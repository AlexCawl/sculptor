import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.core")
}

android {
    namespace = "org.alexcawl.sculptor.common.contract"
}

commonMainDependencies {
    api(projects.common.sculptorCore)
}
