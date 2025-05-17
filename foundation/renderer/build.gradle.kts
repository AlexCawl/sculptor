import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.renderer"
}

commonMainDependencies {
    api(projects.core.renderer)
    api(projects.foundation.layout)
}
