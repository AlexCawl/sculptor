import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.contract"
}

commonMainDependencies {
    // Public dependencies
    api(projects.common.sculptorCore)
}
