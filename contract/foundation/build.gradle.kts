import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kmp.core")
}

android {
    namespace = "org.alexcawl.sculptor.contract.foundation"
}

commonMainDependencies {
    implementation(projects.common)
    implementation(projects.contract.common)
}
