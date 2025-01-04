import org.alexcawl.plugins.kotlin.kotlinMultiplatformConfiguration
import org.alexcawl.plugins.libs

plugins {
    id("com.android.application")

    id("convention.project.kmp")
}

kotlinMultiplatformConfiguration {
    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.android.activityCompose)
                implementation(libs.android.splash)
            }
        }

        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
