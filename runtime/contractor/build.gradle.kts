import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime"
}

commonMainDependencies {
    api(projects.core.contractor)
}
