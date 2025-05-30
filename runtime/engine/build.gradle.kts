import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.engine"
}

commonMainDependencies {
    api(projects.core.contractor)
    implementation(projects.runtime.contractor)
    implementation(projects.internal.diCompose)
    implementation(projects.internal.mviCompose)
}
