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
    ":core:contractor",
)

include(
    ":runtime:engine",
    ":runtime:contractor",
    ":runtime:datasource",
)

include(
    ":foundation:contract",
    ":foundation:contractor",
    ":foundation:bundle",
    ":foundation:showroom",
)

include(
    ":showroom:server",
    ":showroom:client",
)
