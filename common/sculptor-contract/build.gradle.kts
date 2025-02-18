import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.contract"
}

commonMainDependencies {
    // Private dependencies
    implementation(projects.common.sculptorCore)
}
