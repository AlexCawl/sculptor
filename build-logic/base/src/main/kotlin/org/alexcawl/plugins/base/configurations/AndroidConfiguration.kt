package org.alexcawl.plugins.base.configurations

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.alexcawl.plugins.base.extensions.AndroidExtensions
import org.alexcawl.plugins.base.extensions.androidExtension
import org.gradle.api.Project


public inline fun Project.androidBaseConfiguration(
    androidConfigurationBlock: AndroidExtensions.() -> Unit
): Unit = try {
    androidConfigurationBlock(androidExtension)
} catch (ignored: IllegalStateException) {
    project.logger.warn("Android plugin is not applied for ${project.name}")
}

public inline fun Project.androidLibraryConfiguration(
    androidConfigurationBlock: LibraryExtension.() -> Unit
) {
    try {
        when (val libraryExtension: LibraryExtension? = androidExtension as? LibraryExtension) {
            null -> Unit
            else -> androidConfigurationBlock(libraryExtension)
        }
    } catch (ignored: IllegalStateException) {
        project.logger.warn("Android library plugin is not applied for ${project.name}")
    }
}

public inline fun Project.androidApplicationConfiguration(
    androidConfigurationBlock: ApplicationExtension.() -> Unit
) {
    try {
        when (val applicationExtension: ApplicationExtension? = androidExtension as? ApplicationExtension) {
            null -> Unit
            else -> androidConfigurationBlock(applicationExtension)
        }
    } catch (ignored: IllegalStateException) {
        project.logger.warn("Android application plugin is not applied for ${project.name}")
    }
}
