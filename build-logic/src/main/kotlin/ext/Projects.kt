package ext

import org.gradle.api.Project

class AggregateProjects(
    val base: BaseProjects,
    val feature: FeatureProjects,
)

class BaseProjects(private val project: Project) {
    val api: Project
        get() = project.findProjectOrThrow(":base:api")

    val di: Project
        get() = project.findProjectOrThrow(":base:di")

    val presentation: Project
        get() = project.findProjectOrThrow(":base:presentation")

    val impl: Project
        get() = project.findProjectOrThrow(":base:impl")
}

class FeatureProjects(private val project: Project)

val Project.projects: AggregateProjects
    get() {
        return AggregateProjects(
            base = BaseProjects(this),
            feature = FeatureProjects(this)
        )
    }

private fun Project.findProjectOrThrow(path: String): Project = findProject(path)
    ?: throw NoSuchElementException("Library $name not found in version catalog")
