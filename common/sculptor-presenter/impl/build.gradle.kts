import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.presenter"
}

commonMainDependencies {
    api(projects.common.sculptorPresenter.api)
}
