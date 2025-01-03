This is a Kotlin Multiplatform project targeting Android, Desktop.

# Source

* `/app` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.

* `/common` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the
  platform-specific folders here too.

# Build

* `/build-logic` is for the Gradle plugins and project setup.
    - `base` is for the common configurations and extensions.
    - `convention` is for the convention plugins.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…