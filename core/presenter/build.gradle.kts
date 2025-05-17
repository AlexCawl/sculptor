import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.core.presenter"
}

commonMainDependencies {
    api(projects.core.contract)
    api(projects.core.layout)
}
