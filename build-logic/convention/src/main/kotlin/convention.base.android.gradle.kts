@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ManagedVirtualDevice
import org.alexcawl.plugins.android.androidApplicationConfiguration
import org.alexcawl.plugins.android.androidBaseConfiguration
import org.alexcawl.plugins.android.androidLibraryConfiguration
import org.alexcawl.plugins.buildToolsVersionValue
import org.alexcawl.plugins.compileSdkValue
import org.alexcawl.plugins.javaCompileTargetVersionValue
import org.alexcawl.plugins.minSdkValue
import org.alexcawl.plugins.ndkVersionValue
import org.alexcawl.plugins.projectVersionCodeValue
import org.alexcawl.plugins.projectVersionNameValue
import org.alexcawl.plugins.targetSdkValue
import org.alexcawl.plugins.toJavaVersion
import org.gradle.kotlin.dsl.maybeCreate

androidBaseConfiguration {
    compileSdk = compileSdkValue
    ndkVersion = ndkVersionValue

    buildFeatures {
        viewBinding = false
        buildConfig = false
        compose = true
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
        managedDevices.devices {
            maybeCreate<ManagedVirtualDevice>("pixel5").apply {
                device = "Pixel 5"
                apiLevel = targetSdkValue
                systemImageSource = "aosp"
            }
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}
