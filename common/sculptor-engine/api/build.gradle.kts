import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.engine"
}

commonMainDependencies {
    api(projects.common.sculptorContract)
    api(projects.common.sculptorLayout)
    api(projects.common.sculptorPresenter.api)
    api(projects.common.sculptorRenderer.api)
}
