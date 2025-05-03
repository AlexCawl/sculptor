import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.renderer"
}

commonMainDependencies {
    api(projects.core.renderer)
    api(projects.foundation.layout)
}
