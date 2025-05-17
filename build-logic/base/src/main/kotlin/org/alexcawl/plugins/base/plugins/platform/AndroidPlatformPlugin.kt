@file:Suppress("UnstableApiUsage")

package org.alexcawl.plugins.base.plugins.platform

import com.android.build.api.dsl.ManagedVirtualDevice
import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.buildToolsVersionValue
import org.alexcawl.plugins.base.compileSdkValue
import org.alexcawl.plugins.base.configurations.androidApplicationConfiguration
import org.alexcawl.plugins.base.configurations.androidBaseConfiguration
import org.alexcawl.plugins.base.configurations.androidLibraryConfiguration
import org.alexcawl.plugins.base.configurations.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.base.javaCompileTargetVersionValue
import org.alexcawl.plugins.base.minSdkValue
import org.alexcawl.plugins.base.ndkVersionValue
import org.alexcawl.plugins.base.projectVersionCodeValue
import org.alexcawl.plugins.base.projectVersionNameValue
import org.alexcawl.plugins.base.targetSdkValue
import org.alexcawl.plugins.base.toJavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.maybeCreate
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

@InternalBuildLogic
internal class AndroidPlatformPlugin : PlatformPlugin() {
    override fun Project.configure() {
        with(plugins) {
            apply(type = JvmPlatformPlugin::class)
        }

        androidBaseConfiguration {
            compileSdk = compileSdkValue
            ndkVersion = ndkVersionValue

            buildFeatures {
                viewBinding = false
                buildConfig = false
            }

            defaultConfig {
                minSdk = minSdkValue
                buildToolsVersion = buildToolsVersionValue
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            lint {
                targetSdk = targetSdkValue
                lintConfig = rootProject.file("lint.xml")
                checkAllWarnings = true
                warningsAsErrors = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            sourceSets {
                named("main").configure {
                    java.srcDir("src/main/kotlin")
                }
            }

            compileOptions {
                sourceCompatibility = javaCompileTargetVersionValue.toJavaVersion()
                targetCompatibility = javaCompileTargetVersionValue.toJavaVersion()
            }

            //https://developer.android.com/studio/test/gradle-managed-devices
            testOptions {
                managedDevices.devices
                    .maybeCreate<ManagedVirtualDevice>(name = "pixel5")
                    .apply {
                        device = "Pixel 5"
                        apiLevel = targetSdkValue
                        systemImageSource = "aosp"
                    }
            }
        }

        androidLibraryConfiguration {
            // Configure android library
        }

        androidApplicationConfiguration {
            defaultConfig {
                applicationId = ""
                versionCode = projectVersionCodeValue
                versionName = projectVersionNameValue
            }

            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isDebuggable = true
                    applicationIdSuffix = ".debug"
                }

                getByName("release") {
                    isMinifyEnabled = true
                    multiDexEnabled = true
                    isShrinkResources = true
                    proguardFiles(
                        getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        kotlinMultiplatformConfiguration {
            androidTarget {
                @OptIn(ExperimentalKotlinGradlePluginApi::class)
                instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
            }
        }
    }
}
