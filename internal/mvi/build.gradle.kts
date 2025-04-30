import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.libs

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.internal.mvi"
}

commonMainDependencies {
    implementation(libs.common.lifecycle.viewmodel)
    implementation(libs.common.lifecycle.runtime)
}
