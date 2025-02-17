import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.core")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.server"
}

commonMainDependencies {
    api(projects.common.sculptorContract)
}
