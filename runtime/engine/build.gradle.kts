import org.alexcawl.plugins.base.commonMainDependencies

plugins {
    id("convention.client.library")
    `maven-publish`
}

android {
    namespace = "org.alexcawl.sculptor.runtime.engine"
}

commonMainDependencies {
    api(projects.core.contract)
    api(projects.core.layout)
    api(projects.core.presenter)
    api(projects.core.renderer)
    implementation(projects.runtime.presenter)
    implementation(projects.runtime.renderer)
    implementation(projects.internal.diCompose)
    implementation(projects.internal.mviCompose)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])

            groupId = "org.alexcawl.sculptor"
            artifactId = "sculptor.engine"
            version = "0.0.1" // Define your library version

            pom {
                name.set("Sculptor SDUI")
                description.set("Awesome!")
                url.set("https://yourlibrary.url")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("AlexCawl")
                        name.set("AlexCawl")
                        email.set("your.email@example.com")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/AlexCawl/sculptor")
            credentials {
            }
        }
    }
}
