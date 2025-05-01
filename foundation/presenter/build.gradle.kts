import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.presenter"
}

commonMainDependencies {
    api(projects.core.presenter)
    api(projects.foundation.contract)
    api(projects.foundation.layout)
}
