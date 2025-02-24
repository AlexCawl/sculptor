import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.layout"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorCore)
}
