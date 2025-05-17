import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.presenter"
}

commonMainDependencies {
    api(projects.core.presenter)
}
