import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.renderer"
}

commonMainDependencies {
    // Private dependencies
    implementation(projects.common.sculptorRenderer)

    // Public dependencies
    api(projects.foundation.sculptorLayout)
}
