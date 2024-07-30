package ext

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.compose.ComposePlugin.Dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val KotlinMultiplatformExtension.compose: Dependencies
    get() = (this as ExtensionAware).extensions.getByName("compose") as Dependencies

val DependencyHandler.compose: Dependencies
    get() = (this as ExtensionAware).extensions.getByName("compose") as Dependencies