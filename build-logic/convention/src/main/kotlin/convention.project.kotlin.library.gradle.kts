import org.alexcawl.plugins.libs

plugins {
    id("com.android.library")

    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.gitlab.arturbosch.detekt")

    id("convention.base.common")
    id("convention.base.android")
    id("convention.base.kmp")
    id("convention.base.detekt")
}

android {
    buildFeatures {
        compose = false
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.bundles.common.source)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.common.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.bundles.android.source)
            }
        }

        androidInstrumentedTest {
            dependencies {
                implementation(libs.bundles.android.test)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.bundles.jvm.source)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.bundles.jvm.test)
            }
        }
    }
}
