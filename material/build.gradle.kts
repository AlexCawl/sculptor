import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.material"
}

commonMainDependencies {
    implementation(projects.core)
    implementation(compose.dependencies.material)
}
