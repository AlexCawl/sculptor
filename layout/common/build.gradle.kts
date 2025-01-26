import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.layout.common"
}

commonMainDependencies {
    implementation(projects.core)
    implementation(projects.provider)
}
