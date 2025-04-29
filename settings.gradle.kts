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
    ":internal:di"
)

include(
    ":common:sculptor-contract",
    ":common:sculptor-layout",
    ":common:sculptor-presenter:api",
    ":common:sculptor-presenter:impl",
    ":common:sculptor-renderer:api",
    ":common:sculptor-renderer:impl",
    ":common:sculptor-engine:api",
    ":common:sculptor-engine:impl",
    ":common:sculptor-engine:core",
)

//include(
//    ":foundation:sculptor-contract",
//    ":foundation:sculptor-layout",
//    ":foundation:sculptor-presenter",
//    ":foundation:sculptor-renderer",
//    ":foundation:sculptor-client",
//    ":foundation:sculptor-server",
//    ":foundation:showroom",
//)
