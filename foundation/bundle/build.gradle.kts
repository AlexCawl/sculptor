import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.bundle"
}

commonMainDependencies {
    api(projects.foundation.contract)
    api(projects.foundation.layout)
    implementation(projects.foundation.presenter)
    implementation(projects.foundation.renderer)
    implementation(projects.runtime.engine)
}
