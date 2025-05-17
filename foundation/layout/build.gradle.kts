import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.layout"
}

commonMainDependencies {
    api(projects.core.layout)
}
