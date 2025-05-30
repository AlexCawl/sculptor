import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.core"
}

commonMainDependencies {
    api(projects.core.contract)
}
