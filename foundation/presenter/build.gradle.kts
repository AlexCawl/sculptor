import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.presenter"
}

commonMainDependencies {
    api(projects.core.presenter)
    api(projects.foundation.contract)
    api(projects.foundation.layout)
}
