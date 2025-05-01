import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.renderer"
}

commonMainDependencies {
    api(projects.core.renderer)
}
