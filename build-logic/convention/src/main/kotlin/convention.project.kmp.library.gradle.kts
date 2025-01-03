import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration

plugins {
    id("com.android.library")

    id("convention.project.kmp")
}

kotlinMultiplatformConfiguration {
    sourceSets {
        jvmMain {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}
