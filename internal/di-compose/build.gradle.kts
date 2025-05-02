import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.internal.di.compose"
}

commonMainDependencies {
    api(projects.internal.di)
}
