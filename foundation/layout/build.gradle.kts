import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.layout"
}

commonMainDependencies {
    api(projects.core.layout)
}
