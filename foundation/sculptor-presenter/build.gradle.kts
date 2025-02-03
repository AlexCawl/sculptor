import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.presenter"
}

commonMainDependencies {
    api(projects.common.sculptorPresenter)
    implementation(projects.foundation.sculptorContract)
    implementation(projects.foundation.sculptorLayout)
}
