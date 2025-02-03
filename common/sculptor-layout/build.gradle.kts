import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.layout"
}

commonMainDependencies {
    api(projects.common.sculptorCore)
}
