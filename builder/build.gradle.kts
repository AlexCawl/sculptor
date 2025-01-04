import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.builder"
}

commonMainDependencies {
    implementation(projects.core)
}
