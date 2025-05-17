@file:Suppress("UnstableApiUsage")

rootProject.name = "sculptor"

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

include(
    ":internal:di",
    ":internal:di-compose",
    ":internal:mvi",
    ":internal:mvi-compose",
)

include(
    ":core:contract",
    ":core:layout",
    ":core:presenter",
    ":core:renderer",
)

include(
    ":runtime:engine",
    ":runtime:presenter",
    ":runtime:renderer",
    ":runtime:datasource",
)

include(
    ":foundation:contract",
    ":foundation:layout",
    ":foundation:presenter",
    ":foundation:renderer",
    ":foundation:bundle",
    ":foundation:showroom",
)

include(
    ":showroom:server",
    ":showroom:client",
)
