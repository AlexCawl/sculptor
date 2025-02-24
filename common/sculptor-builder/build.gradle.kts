import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.builder"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorCore)
    api(projects.common.sculptorContract)
}
