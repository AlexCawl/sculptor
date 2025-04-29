import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.common.engine"
}

commonMainDependencies {
    api(projects.common.sculptorEngine.api)
    implementation(projects.common.sculptorPresenter.impl)
    implementation(projects.common.sculptorRenderer.impl)
    implementation(projects.internal.di)
}
