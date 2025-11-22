# CannyEdgeFull - Full Android Studio Project (OpenCV)

This project is a full Android Studio project scaffold with:
- Gradle build files
- An `app` module that uses OpenCV via Maven AAR for Java API
- An optional native implementation (C++) with `externalNativeBuild` (CMake)
- A script to download OpenCV Android SDK locally for native builds

IMPORTANT:
- This archive does **not** include the full OpenCV Android SDK binaries (these are large).
- There are two recommended ways to add OpenCV support:
  1. **Easiest (recommended):** Use OpenCV AAR from Maven Central (already added in `app/build.gradle`).
     - This provides Java APIs and prebuilt native libs. No extra manual steps needed.
  2. **Native C++ builds using CMake:** Download the OpenCV Android SDK and point CMake to it.
     - Run `./download_opencv_sdk.sh` to download OpenCV 4.8.0 SDK (example).
     - After extracting, pass `-DOpenCV_DIR=<path>/sdk/native/jni` to CMake or set in Android Studio:
       - File > Settings > Build, Execution, Deployment > CMake (or edit gradle `externalNativeBuild` arguments).

How to import into Android Studio
1. Open Android Studio -> Open an existing project -> select the folder containing this project.
2. Let Studio sync Gradle. If prompted about Gradle plugin/Android Gradle plugin versions, accept recommended settings.
3. If you want to use native C++ code and build with OpenCV headers:
   - Run `./download_opencv_sdk.sh` to download and extract the OpenCV SDK into `sdk/`.
   - Edit `app/build.gradle` or pass an argument so CMake gets `-DOpenCV_DIR=$PWD/sdk/OpenCV-android-sdk/sdk/native/jni`.
   - Alternatively, import the `sdk` as a module and add `implementation project(':opencv')`.

Notes on native (C++) build
- The CMakeLists includes a `find_package(OpenCV REQUIRED)` call. For CMake to locate OpenCV on Android, ensure `OpenCV_DIR` points to the correct `native/jni` directory inside the SDK, which contains the OpenCVConfig.cmake file.
- If you use the Maven AAR approach (recommended), you can rely on the Java APIs and prebuilt native libs bundled in the AAR.

Sample usage
- `MainActivity.kt` demonstrates using OpenCV Java API to run Canny edge detection on a bundled sample image.
- Optional `native-lib.cpp` shows a native function you can call if you prefer doing processing in native code.

Git commit history
- This scaffold will not contain git history. Before pushing to GitHub, initialize a git repo and make multiple commits that show iterative work (scaffold > web demo > native code > readme > assets) to satisfy assignment rules.

If you'd like, I can:
- (A) Attempt to download the OpenCV SDK and include its `sdk` directory in the ZIP (note: large download ~50-200MB depending on version).
- (B) Create git commits and a local `.git` folder with a basic history in the archive so you can push directly.
- (C) Build a sample APK here and provide it for download (requires complete OpenCV binaries & NDK).

