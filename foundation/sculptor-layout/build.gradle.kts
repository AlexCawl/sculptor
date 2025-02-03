import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.layout"
}

commonMainDependencies {
    api(projects.common.sculptorLayout)
}
