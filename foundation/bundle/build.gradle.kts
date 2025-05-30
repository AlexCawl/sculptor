import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.bundle"
}

commonMainDependencies {
    implementation(projects.foundation.contractor)
    implementation(projects.runtime.engine)
}
