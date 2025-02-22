import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvm.desktop

plugins {
    id("convention.project.showroom.application")
}

val packageNamespace = "org.alexcawl.sculptor.foundation.showroom"

android {
    namespace = packageNamespace

    defaultConfig {
        applicationId = "org.alexcawl.showroom.app"
    }
}

desktop(
    applicationClass = "$packageNamespace.MainKt",
    applicationPackage = packageNamespace
)

commonMainDependencies {
    implementation(projects.foundation.sculptorClient)
    implementation(projects.foundation.sculptorServer)
}
