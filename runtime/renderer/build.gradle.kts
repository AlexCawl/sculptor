import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.renderer"
}

commonMainDependencies {
    api(projects.core.renderer)
}
