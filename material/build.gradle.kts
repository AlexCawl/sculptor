plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.material"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material)
            }
        }
    }
}
