import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation"
}

commonMainDependencies {
    api(projects.foundation.contract)
    api(projects.core.contractor)
}
