import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.jvm.desktop

plugins {
    id("convention.project.kmp.application")
}

android {
    namespace = "org.alexcawl.showroom.app"

    defaultConfig {
        applicationId = "org.alexcawl.showroom.app"
    }
}

desktop(
    applicationClass = "org.alexcawl.showroom.app.MainKt",
    applicationPackage = "org.alexcawl.showroom.app"
)

commonMainDependencies {
    implementation(projects.core)
    implementation(projects.provider)
    implementation(projects.foundation)
    implementation(projects.modifier)
    implementation(projects.builder)
}
