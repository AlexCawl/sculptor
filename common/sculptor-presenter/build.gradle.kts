import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.presenter"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorContract)
    api(projects.common.sculptorLayout)
}
