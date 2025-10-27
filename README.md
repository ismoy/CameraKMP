

# ImagePickerKMP Usage Demo

This project is a demo for the **ImagePickerKMP** library. It showcases how to leverage all the features and options provided by the library to select images and take photos on both Android and iOS, using Kotlin Multiplatform, Jetpack Compose, and SwiftUI.

It includes practical examples of every available feature in the library, demonstrating how to integrate and customize them in a multiplatform app.

## What does this demo show?

- How to select one or multiple images from the gallery.
- How to capture photos using the device camera.
- How to handle camera and gallery permissions, including custom dialogs and access to settings.
- Example of multiplatform integration (Android/iOS) using the library.
- Customizing the user experience with the library's components.

## Project structure

- `composeApp/`: Android usage example and shared multiplatform code.
- `iosApp/`: iOS usage example.
- Multiplatform configuration files and this README.

## Main dependencies
- [ImagePickerKMP](https://github.com/ismoy/ImagePickerKMP) (main library showcased)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Coil](https://coil-kt.github.io/coil/compose/) for image loading in Compose

## How to run the demo

### Android
1. Open the project in Android Studio.
2. Run the `composeApp` module on an emulator or physical device.

### iOS
1. Open `iosApp/iosApp.xcodeproj` in Xcode.
2. Run on a simulator or physical device.

## Customization & examples
- You can modify the permission dialogs in `CustomPermissionDialog.kt` and `CustomGoToSettingsDialog.kt`.
- The image selection/capture logic is in the `ImagePickerLauncher` and `GalleryPickerLauncher` components.
- Explore the code to see how to use every option of the library.

## Credits
Demo developed by ismoy , showcasing the full usage of the ImagePickerKMP library.

---
