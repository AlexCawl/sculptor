import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.bundle"
}

commonMainDependencies {
    api(projects.foundation.contract)
    api(projects.foundation.layout)
    implementation(projects.foundation.presenter)
    implementation(projects.foundation.renderer)
}
