import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.shared.library")
}

android {
    namespace = "org.alexcawl.sculptor.foundation.contract"
}

commonMainDependencies {
    api(projects.core.contract)
}
