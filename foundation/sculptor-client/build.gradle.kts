import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.client"
}

commonMainDependencies {
    // Common
    api(projects.common.sculptorEngine)
    api(projects.common.sculptorContract)
    api(projects.common.sculptorPresenter)
    api(projects.common.sculptorLayout)

    // Foundation
    api(projects.foundation.sculptorContract)
    api(projects.foundation.sculptorPresenter)
    api(projects.foundation.sculptorLayout)
}
