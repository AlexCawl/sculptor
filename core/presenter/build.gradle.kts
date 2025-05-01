import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.core.presenter"
}

commonMainDependencies {
    api(projects.core.contract)
    api(projects.core.layout)
}
