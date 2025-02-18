import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.client"
}

commonMainDependencies {
    // Private dependencies
    implementation(projects.foundation.sculptorPresenter)
    implementation(projects.foundation.sculptorRenderer)

    // Public dependencies [common]
    api(projects.common.sculptorEngine)
    api(projects.common.sculptorContract)
    api(projects.common.sculptorPresenter)
    api(projects.common.sculptorLayout)

    // Public dependencies [foundation]
    api(projects.foundation.sculptorContract)
    api(projects.foundation.sculptorLayout)
}
