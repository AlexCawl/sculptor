import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.renderer"
}

commonMainDependencies {
    // Private dependencies
    implementation(projects.common.sculptorCore)

    // Public dependencies
    api(projects.common.sculptorLayout)
}
