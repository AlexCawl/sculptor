import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.modifier"
}

commonMainDependencies {
    implementation(projects.core)
    implementation(projects.provider)
}
