import org.alexcawl.plugins.commonMainDependencies

plugins {
    id("convention.project.kotlin.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.server"
}

commonMainDependencies {
    // Public dependencies [common]
    api(projects.common.sculptorContract)
    api(projects.common.sculptorBuilder)

    // Public dependencies [foundation]
    api(projects.foundation.sculptorContract)
}
