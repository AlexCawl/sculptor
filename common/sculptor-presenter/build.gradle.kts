import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.skulptor.common.presenter"
}

commonMainDependencies {
    api(projects.common.sculptorCore)
}
