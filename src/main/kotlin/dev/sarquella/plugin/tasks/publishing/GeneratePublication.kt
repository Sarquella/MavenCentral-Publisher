package dev.sarquella.plugin.tasks.publishing

import dev.sarquella.plugin.helper.extensions.isAndroidLibrary
import dev.sarquella.plugin.helper.extensions.params
import dev.sarquella.plugin.helper.extensions.publishing
import dev.sarquella.plugin.tasks.sources.GenerateSourceJar
import org.gradle.api.DefaultTask
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskAction
import java.net.URI

abstract class GeneratePublication : DefaultTask() {

    private val loadCredentials = project.tasks.withType(LoadCredentials::class.java).first()
    private val sourceJar = project.tasks.withType(GenerateSourceJar::class.java).first()

    init {
        dependsOn.add(loadCredentials)
    }

    @TaskAction
    fun generate() {
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
            publication.artifact(sourceJar.destinationDirectory) { artifact ->
                artifact.builtBy(sourceJar)
            }

            publication.pom { pom ->
                pom.name.set(params.artifact.get())
                pom.description.set(params.description.get())
                pom.url.set(params.webUrl.get())

                pom.licenses { licenses ->
                    licenses.license { license ->
                        license.name.set(params.licenseName.get())
                        license.url.set(params.licenseUrl.get())
                    }
                }

                pom.developers { devs ->
                    devs.developer { dev ->
                        dev.id.set(params.developerId.get())
                        dev.name.set(params.developerName.get())
                        dev.email.set(params.developerEmail.get())
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
                    project.configurations.getByName("implementation").allDependencies.forEach { dependency ->
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