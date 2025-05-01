import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.engine"
}

commonMainDependencies {
    api(projects.core.contract)
    api(projects.core.layout)
    api(projects.core.presenter)
    api(projects.core.renderer)
    implementation(projects.runtime.presenter)
    implementation(projects.runtime.renderer)
    implementation(projects.internal.di)
    implementation(projects.internal.mvi)
}
