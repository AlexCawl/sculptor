@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

include(":showroom:app")

include(
    ":common:sculptor-core",
    ":common:sculptor-contract",
    ":common:sculptor-layout",
    ":common:sculptor-presenter",
    ":common:sculptor-renderer",
//    ":common:sculptor-builder",
    ":common:sculptor-engine",
)

include(
    ":foundation:sculptor-contract",
    ":foundation:sculptor-layout",
    ":foundation:sculptor-presenter",
    ":foundation:sculptor-renderer",
    ":foundation:sculptor-client",
    ":foundation:showroom",
)
