package org.alexcawl.plugins.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

inline fun Project.androidBaseConfiguration(
    androidConfigurationBlock: AndroidExtensions.() -> Unit
) = androidConfigurationBlock(androidExtension)

inline fun Project.androidLibraryConfiguration(
    androidConfigurationBlock: LibraryExtension.() -> Unit
) {
    val libraryExtension: LibraryExtension? = extensions.findByType(LibraryExtension::class)
    when (libraryExtension) {
        null -> Unit
        else -> androidConfigurationBlock(libraryExtension)
    }
}

inline fun Project.androidApplicationConfiguration(
    androidConfigurationBlock: ApplicationExtension.() -> Unit
) {
    val applicationExtension: ApplicationExtension? = extensions.findByType(ApplicationExtension::class)
    when (applicationExtension) {
        null -> Unit
        else -> androidConfigurationBlock(applicationExtension)
    }
}
