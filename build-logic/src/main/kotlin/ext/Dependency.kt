package ext

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)