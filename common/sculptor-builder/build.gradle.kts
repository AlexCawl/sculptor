import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.builder"
}

commonMainDependencies {
    // Private dependencies
    implementation(projects.common.sculptorCore)

    // Public dependencies
    api(projects.common.sculptorContract)
}
