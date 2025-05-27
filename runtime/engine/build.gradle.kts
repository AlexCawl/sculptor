import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.runtime.engine"
}

commonMainDependencies {
    implementation(projects.core.contract)
    implementation(projects.core.layout)
    implementation(projects.core.presenter)
    implementation(projects.core.renderer)
    implementation(projects.runtime.presenter)
    implementation(projects.runtime.renderer)
    implementation(projects.internal.diCompose)
    implementation(projects.internal.mviCompose)
}
