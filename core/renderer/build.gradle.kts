import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.core.renderer"
}

commonMainDependencies {
    api(projects.core.layout)
}
