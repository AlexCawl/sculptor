import org.alexcawl.plugins.commonMainDependencies
import org.alexcawl.plugins.libs

plugins {
    id("convention.project.compose.library")
}

android {
    namespace = "org.alexcawl.sculptor.internal.mvi.compose"
}

commonMainDependencies {
    api(projects.internal.mvi)
}
