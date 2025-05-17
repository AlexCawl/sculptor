plugins {
    `kotlin-dsl`
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(projects.base)

    // Kotlin plugins
    implementation(libs.gradleplugin.kotlinMultiplatform)
    implementation(libs.gradleplugin.composeMultiplatform)
    implementation(libs.gradleplugin.composeCompiler)
    implementation(libs.gradleplugin.kotlinSerialization)

    // Android plugins
    implementation(libs.gradleplugin.android)

    // Jvm plugins
    implementation(libs.gradleplugin.kotlinJvm)

    // Utility plugins
    implementation(libs.gradleplugin.detekt)

    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        // Server plugins
        register("convention.server.library") {
            id = "convention.server.library"
            implementationClass = "org.alexcawl.plugins.convention.plugins.ServerLibraryConvention"
        }

        register("convention.server.application") {
            id = "convention.server.application"
            implementationClass = "org.alexcawl.plugins.convention.plugins.ServerApplicationConvention"
        }

        // Client plugins
        register("convention.client.library") {
            id = "convention.client.library"
            implementationClass = "org.alexcawl.plugins.convention.plugins.CmpLibraryConvention"
        }

        register("convention.client.application") {
            id = "convention.client.application"
            implementationClass = "org.alexcawl.plugins.convention.plugins.CmpApplicationConvention"
        }

        // Shared plugins
        register("convention.shared.library") {
            id = "convention.shared.library"
            implementationClass = "org.alexcawl.plugins.convention.plugins.KmpLibraryConvention"
        }

        register("convention.shared.application") {
            id = "convention.shared.application"
            implementationClass = "org.alexcawl.plugins.convention.plugins.KmpApplicationConvention"
        }
    }
}
