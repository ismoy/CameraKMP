

# ImagePickerKMP Usage Demo

This project is a demo for the **ImagePickerKMP** library. It showcases how to leverage all the features and options provided by the library to select images and take photos on both Android and iOS, using Kotlin Multiplatform, Jetpack Compose, and SwiftUI.

It includes practical examples of every available feature in the library, demonstrating how to integrate and customize them in a multiplatform app.

---
## Demos Android, iOS, Desktop y Web

<table>
  <tr>
    <th>Cámara Android</th>
    <th>Recorte Android</th>
    <th>Cámara iOS</th>
    <th>Recorte iOS</th>
    <th>OCR</th>
  </tr>
  <tr>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/androidCameraDemo.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/androidCameraDemo.gif" alt="Demo Cámara Android" width="200"></a></td>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/androidCrop.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/androidCrop.gif" alt="Demo Recorte Android" width="200"></a></td>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/iosCameraDemo.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/iosCameraDemo.gif" alt="Demo Cámara iOS" width="200"></a></td>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/iosCrop.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/iosCrop.gif" alt="Demo Recorte iOS" width="200"></a></td>
  </tr>
  <tr>
    <th>Desktop</th>
    <th>Web</th>
    <th colspan="2"></th>
  </tr>
  <tr>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/DesktopDemo.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/DesktopDemo.gif" alt="Demo Desktop" width="300"></a></td>
    <td><a href="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/demoWeb.gif"><img src="https://raw.githubusercontent.com/ismoy/ImagePickerKMP/develop/docs/demoWeb.gif" alt="Demo Web" width="300"></a></td>
    <td colspan="2"></td>
  </tr>

<tr>
    <td><a href="https://raw.githubusercontent.com/ismoy/CameraKMP/main/OCR_test.gif"><img src="https://raw.githubusercontent.com/ismoy/CameraKMP/main/OCR_test.gif" alt="Demo Desktop" width="300"></a></td>
  </tr>
</table>

---
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
