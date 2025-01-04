plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.material3"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
            }
        }
    }
}
