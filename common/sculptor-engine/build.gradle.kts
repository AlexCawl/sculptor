import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.engine"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorCore)
    api(projects.common.sculptorContract)
    api(projects.common.sculptorLayout)
    api(projects.common.sculptorPresenter)
    api(projects.common.sculptorRenderer)
}
