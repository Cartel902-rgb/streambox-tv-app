# Build your APK in the cloud (no Android Studio needed)

GitHub's free servers will compile the APK for you in ~5 minutes.

## Step 1 — Create a free GitHub account
Go to https://github.com and sign up (free, takes 2 minutes).

## Step 2 — Create a repository
- Click the **+** (top right) → **New repository**
- Name it `cartel-tv-app`
- Keep it **Public**
- Click **Create repository**

## Step 3 — Upload the project
- On the new repo page click the link that says **"uploading an existing file"**
  (or **Add file → Upload files**)
- Open the `cartel-tv-app` folder on your computer, select EVERYTHING
  inside it (the folders `app` and `.github`, plus `build.gradle`,
  `settings.gradle`, `gradle.properties`, `README.md`) and drag it all
  into the GitHub page.
- Wait for the upload to finish, then click **Commit changes** (green button).

## Step 4 — Build it
- Click the **Actions** tab at the top of the repo page.
- You'll see "Build Cartel TV APK" on the left → click it →
  **Run workflow** (the button on the right) → **Run workflow** again.
- Wait for the yellow dot to turn into a green checkmark (about 5 minutes).
  Click it if you want to watch the logs.

## Step 5 — Download your APK
- Click the finished run → scroll down to **Artifacts** →
  click **carteltv-apk** to download.
- Unzip it → you get `app-debug.apk`.
- **Rename it to `carteltv.apk`** and copy it into your StreamBox
  **`public`** folder (next to index.html).

## Step 6 — Install on Fire TV
- Fire TV: **Settings → My Fire TV → About** → click your device name **7 times** (enables Developer Options)
- **Settings → My Fire TV → Developer Options** → turn on **Install unknown apps** for Downloader
- Install the free **Downloader** app from the Amazon Appstore
- Open Downloader and type:

      http://YOUR-COMPUTER-IP:3000/carteltv.apk

- Download → Install → open StreamBox from Settings → Applications.

The app will ask for your server address once (http://YOUR-COMPUTER-IP:3000)
and then it works like a real streaming app with your Fire TV remote.
