# scanme-calculator

This Android project is built with Kotlin and follows modern Android development best practices. It incorporates various technologies and architectural patterns for a robust and maintainable codebase.

## Project OverView

- **Language:** Kotlin
- **Build System:** Gradle Kotlin DSL (build.gradle.kts)
- **Dependency Injection:** Dagger Hilt
- **Concurrency:** Kotlin Coroutines and LiveData
- **Architecture Pattern:** MVVM (Model-View-ViewModel)
- **Modularization:** Multi-modules architecture with the following modules:
    - **libs:** Contains custom toolbar and camera border view.
    - **Common:** Holds utility classes, extensions, and interfaces for navigation using Navigation Safe Args.
    - **feature/camera:** Contains the camera fragment.
    - **feature/result:** Handles the scanning and summary of results.
    - **Main app:** Includes Dependency Injection setup and the main activity.

## Build Flavors

This app supports four different types of APKs generated by build flavors:

1. **GreenBuiltInCamera:** APK with features optimized for Green devices using the built-in camera.
2. **GreenFileSystem:** APK with features optimized for Green devices using the file system.
3. **RedBuiltInCamera:** APK with features optimized for Red devices using the built-in camera.
4. **RedFileSystem:** APK with features optimized for Red devices using the file system.

## Getting Started

Follow these steps to set up and run the project on your local machine:

1. **Clone the Repository:** Use Git to clone this repository to your local machine:

   ```bash
      git clone https://github.com/your-username/your-repository.git
   ```

2. **Open in Android Studio:** Open Android Studio and select "Open an existing Android Studio project." Navigate to the cloned repository and open the project.

3. **Build and Run:** Build and run the app on your preferred emulator or physical device. Make sure to select the desired build flavor (GreenBuiltInCamera, GreenFileSystem, RedBuiltInCamera, or RedFileSystem) in the build variant settings.

## Dependencies

This project uses the following key dependencies:

- [Kotlin](https://kotlinlang.org/)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Navigation Component](https://developer.android.com/guide/navigation)

You can find more details about the project's dependencies in the `buildSrc/build.gradle.kts` files of the respective modules.



