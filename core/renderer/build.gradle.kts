import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.core.renderer"
}

commonMainDependencies {
    api(projects.core.layout)
}
