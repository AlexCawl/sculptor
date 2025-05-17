import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.internal.mvi.compose"
}

commonMainDependencies {
    api(projects.internal.mvi)
}
