import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.contractor"
}

commonMainDependencies {
    api(projects.core.contractor)
}
