package org.alexcawl.plugins.base.plugins

import com.vanniktech.maven.publish.SonatypeHost
import org.alexcawl.plugins.base.InternalBuildLogic
import org.alexcawl.plugins.base.configurations.PublishConfiguration
import org.alexcawl.plugins.base.extensions.publishExtension
import org.gradle.api.Project

@InternalBuildLogic
public abstract class PublishModulePlugin : ModulePlugin() {
    public abstract val publishConfiguration: PublishConfiguration

    override fun Project.configure() {
        with(plugins) {
            apply(PUBLISH_PLUGIN_ID)
        }
        with(publishConfiguration) {
            publishExtension.apply {
                coordinates(
                    groupId = projectGroup,
                    artifactId = projectName,
                    version = projectVersion,
                )
                pom {
                    name.set(projectName)
                    description.set(projectDescription)
                    url.set(projectUrl)

                    licenses {
                        license {
                            name.set(license)
                            url.set(licenseUrl)
                        }
                    }

                    developers {
                        developer {
                            id.set(developerId)
                            name.set(developerName)
                            email.set(developerEmail)
                        }
                    }

                    scm {
                        connection.set(scmConnection)
                        developerConnection.set(scmDeveloperConnection)
                        url.set(scmUrl)
                    }

                }

                publishToMavenCentral(
                    host = SonatypeHost.CENTRAL_PORTAL,
                    automaticRelease = true,
                )
            }
        }
    }

    private companion object {
        private const val PUBLISH_PLUGIN_ID = "com.vanniktech.maven.publish"
    }
}