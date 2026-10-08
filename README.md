# StreamBox TV — Fire TV / Android TV app

This is a real TV app (not the browser). It opens your StreamBox server
fullscreen and works with the Fire TV remote. You build one APK file,
host it on your own StreamBox server, and install it on your Fire TV
with the Downloader app.

## Part 1 — Build the APK (one time, on your computer)

1. Install **Android Studio**: https://developer.android.com/studio
   (free; ~1 GB download; keep all default options checked).
2. Open Android Studio → **Open** → select this `cartel-tv-app` folder.
3. Wait for it to sync (a progress bar at the bottom — first sync downloads
   stuff, give it a few minutes). If it asks to upgrade anything, click **More** → **Don't ask again** / **Remind me later**.
4. Build the APK:
   **Build** menu → **Build Bundle(s) / APK(s)** → **Build APK(s)**.
5. When it finishes, click **"locate"** in the popup. You'll find:
   `app/build/outputs/apk/release/app-release.apk` (or `debug/` folder if that's what built —
   either works).
6. **Rename it to `carteltv.apk`** and copy it into your StreamBox folder:
   put it in the **`public`** folder next to `index.html`.
   Now your server serves it at:  `http://YOUR-COMPUTER-IP:3000/carteltv.apk`

## Part 2 — Install it on your Fire TV

1. On the Fire TV: **Settings → My Fire TV → About** → click **Fire TV Stick**
   (or your device name) **7 times** quickly → you are now a developer.
2. **Settings → My Fire TV → Developer Options** → turn on
   **ADB debugging** and **Install unknown apps** (allow it for Downloader).
3. Install the free **Downloader** app from the Amazon Appstore (search "Downloader",
   orange icon).
4. Open Downloader, in the URL box type your server address:

   **http://YOUR-COMPUTER-IP:3000/carteltv.apk**

   (same IP you use in the browser, e.g. `http://192.168.1.50:3000/carteltv.apk`)

5. It downloads → click **Install** → **Done** → delete the APK from
   Downloader when asked (saves space).
6. Find StreamBox: **Settings → Applications → Manage Installed Applications →
   StreamBox → Launch**. To pin it to the home screen: hold the **Home** button
   on the remote → **Apps** → highlight StreamBox → press the **menu (≡)** button →
   **Move** / **Add to Favorites**.

## Part 3 — First launch

- The app asks for your server address — type it once
  (e.g. `http://192.168.1.50:3000`) and it remembers it forever.
- Sign in with your StreamBox account. Navigate with the remote's D-pad;
  the focused item glows cyan.
- To change the server address later: press the **Menu (≡)** button on the remote.
- To update the app later: rebuild the APK, bump it into `public/`, and
  re-run the Downloader step on the TV (you may need to uninstall the old one first).

## Notes

- Requires StreamBox server running on your home network (same as the browser version).
- Works on Fire TV Stick, Fire TV Stick 4K, Fire TV Cube, and most Android TVs too.
- No ads, no tracking — it literally just loads your own server.
