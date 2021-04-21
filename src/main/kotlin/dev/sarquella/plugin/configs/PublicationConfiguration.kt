package dev.sarquella.plugin.configs

import dev.sarquella.plugin.configs.base.Configuration
import dev.sarquella.plugin.helper.extensions.isAndroidLibrary
import dev.sarquella.plugin.helper.extensions.params
import dev.sarquella.plugin.helper.extensions.publishing
import dev.sarquella.plugin.helper.extensions.task
import dev.sarquella.plugin.tasks.GenerateJavadocJar
import dev.sarquella.plugin.tasks.GenerateSourceJar
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import java.net.URI

object PublicationConfiguration : Configuration {

    override fun configure(project: Project) {
        val params = project.params
        val publishing = project.publishing

        publishing.publications.create("release", MavenPublication::class.java) { publication ->
            publication.groupId = params.groupId.get()
            publication.artifactId = params.artifact.get()
            publication.version = params.version.get()

            if (project.isAndroidLibrary) {
                publication.artifact("${project.buildDir}/outputs/aar/${project.name}-release.aar")
            } else {
                publication.artifact("${project.buildDir}/libs/${project.name}-${params.version.get()}.jar")
            }
            publication.artifact(project.task(GenerateSourceJar::class.java))
            publication.artifact(project.task(GenerateJavadocJar::class.java))


            publication.pom { pom ->
                pom.name.set(params.artifact.get())
                pom.description.set(params.description.orNull ?: "")
                pom.url.set(params.webUrl.orNull ?: params.repoUrl.get())

                pom.packaging = if(project.isAndroidLibrary) "aar" else "jar"

                pom.licenses { licenses ->
                    licenses.license { license ->
                        license.name.set(params.license.name.get())
                        license.url.set(params.license.url.get())
                    }
                }

                pom.developers { devs ->
                    devs.developer { dev ->
                        dev.id.set(params.developer.id.get())
                        dev.name.set(params.developer.name.get())
                        dev.email.set(params.developer.email.get())
                    }
                }

                pom.scm { scm ->
                    @Suppress("HttpUrlsUsage")
                    val connectionUrl = params.vcsUrl.get()
                        .replace("http://", "")
                        .replace("https://", "")

                    scm.connection.set("scm:git:$connectionUrl")
                    scm.developerConnection.set("scm:git:ssh://$connectionUrl")
                    scm.url.set(params.repoUrl.get())
                }

                // Include any needed transitive dependencies
                pom.withXml { xml ->
                    val dependenciesNode = xml.asNode().appendNode("dependencies")
                    project.configurations.getByName("implementation").allDependencies
                        .filterNot { it.group == null }
                        .forEach { dependency ->
                            val dependencyNode = dependenciesNode.appendNode("dependency")
                            dependencyNode.appendNode("groupId", dependency.group)
                            dependencyNode.appendNode("artifactId", dependency.name)
                            dependencyNode.appendNode("version", dependency.version)
                        }
                }

            }

        }

        publishing.repositories.maven { repo ->
            repo.name = "mavencentral"
            repo.url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            repo.credentials { credentials ->
                credentials.username = project.extensions.extraProperties["ossrhUsername"]?.toString()
                credentials.password = project.extensions.extraProperties["ossrhPassword"]?.toString()
            }
        }
    }

}