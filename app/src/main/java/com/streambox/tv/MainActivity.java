package com.streambox.tv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private static final String PREFS = "streambox";
    private static final String KEY_URL = "server_url";

    private WebView webView;
    private FrameLayout fullscreenContainer;
    private View customView;
    private WebChromeClient.CustomViewCallback customCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        fullscreenContainer = findViewById(R.id.fullscreen_container);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false); // let videos autoplay
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // keep everything inside the app
                return !url.startsWith("http://") && !url.startsWith("https://");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                if (customView != null) { callback.onCustomViewHidden(); return; }
                customView = view;
                customCallback = callback;
                fullscreenContainer.addView(view,
                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                     FrameLayout.LayoutParams.MATCH_PARENT));
                fullscreenContainer.setVisibility(View.VISIBLE);
                fullscreenContainer.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
            }

            @Override
            public void onHideCustomView() {
                if (customView == null) return;
                fullscreenContainer.removeView(customView);
                fullscreenContainer.setVisibility(View.GONE);
                customView = null;
                if (customCallback != null) { customCallback.onCustomViewHidden(); customCallback = null; }
            }
        });

        String url = getUrl();
        if (url == null) {
            askForUrl(); // first run
        } else {
            webView.loadUrl(url);
        }
    }

    private String getUrl() {
        SharedPreferences p = getSharedPreferences(PREFS, MODE_PRIVATE);
        String u = p.getString(KEY_URL, null);
        return (u == null || u.trim().isEmpty()) ? null : u.trim();
    }

    private void askForUrl() {
        final EditText input = new EditText(this);
        input.setHint("http://192.168.1.50:3000");
        input.setTextSize(18);
        input.setSingleLine(true);
        int pad = (int) (24 * getResources().getDisplayMetrics().density);
        input.setPadding(pad, pad, pad, pad);

        new AlertDialog.Builder(this)
                .setTitle("Cartel TV — Server Address")
                                .setMessage("Enter the address of your StreamBox server\n(same one you use in the browser).")
                .setView(input)
                .setCancelable(false)
                .setPositiveButton("Connect", (d, w) -> {
                    String u = input.getText().toString().trim();
                    if (!u.startsWith("http")) u = "http://" + u;
                    if (!u.contains(":")) u = u + ":3000";
                    getSharedPreferences(PREFS, MODE_PRIVATE).edit().putString(KEY_URL, u).apply();
                    webView.loadUrl(u);
                })
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Fire TV "Menu" button = change server address
        if (keyCode == KeyEvent.KEYCODE_MENU) { askForUrl(); return true; }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (customView != null) {
                webView.getWebChromeClient().onHideCustomView();
                return true;
            }
            if (webView.canGoBack()) { webView.goBack(); return true; }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() { super.onPause(); webView.onPause(); }
    @Override
    protected void onResume() { super.onResume(); webView.onResume(); }
}
