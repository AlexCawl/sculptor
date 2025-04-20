import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.renderer"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorLayout)
}
