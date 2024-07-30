package ext

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

class AggregateCatalog(
    val libs: LibsCatalog,
    val plugins: PluginsCatalog,
    val versions: VersionsCatalogs,
)

class LibsCatalog(private val versionCatalog: VersionCatalog) {

    val kotlinxCoroutinesCore: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("kotlinx-coroutines-core")

    val kotlinxSerializationCore: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("kotlinx-serialization-core")

    val decompose: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("decompose")

    val mvikotlin: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("mvikotlin")

    val mvikotlinMain: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("mvikotlin-main")

    val mvikotlinExtensionsCoroutines: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("mvikotlin-extensions-coroutines")

    val mvikotlinTimetravel: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("mvikotlin-timetravel")

    val essentyLifecycle: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("essenty-lifecycle")

    val koinCore: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("koin-core")

    val androidxActivityCompose: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("androidx-activity-compose")

    val koinAndroid: Provider<MinimalExternalModuleDependency>
        get() = versionCatalog.findLibraryOrThrow("koin-android")
}

class PluginsCatalog(private val versionCatalog: VersionCatalog) {
    val kotlinMultiplatform: Provider<PluginDependency>
        get() = versionCatalog.findPluginProviderOrThrow("kotlinMultiplatform")

    val androidLibrary: Provider<PluginDependency>
        get() = versionCatalog.findPluginProviderOrThrow("androidLibrary")

    val kotlinSerialization: Provider<PluginDependency>
        get() = versionCatalog.findPluginProviderOrThrow("kotlin-serialization")

    val composeCompiler: Provider<PluginDependency>
        get() = versionCatalog.findPluginProviderOrThrow("compose-compiler")

    val jetbrainsCompose: Provider<PluginDependency>
        get() = versionCatalog.findPluginProviderOrThrow("jetbrainsCompose")
}

class VersionsCatalogs(private val versionCatalog: VersionCatalog) {
    val androidCompileSdk: Int
        get() = versionCatalog.findVersionOrThrow("android-compileSdk").toInt()

    val androidMinSdk: Int
        get() = versionCatalog.findVersionOrThrow("android-minSdk").toInt()
}

val Project.versionCatalog: AggregateCatalog
    get() {
        val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
        val libs = LibsCatalog(versionCatalog)
        val plugins = PluginsCatalog(versionCatalog)
        val versions = VersionsCatalogs(versionCatalog)
        return AggregateCatalog(libs, plugins, versions)
    }

private fun VersionCatalog.findPluginProviderOrThrow(name: String): Provider<PluginDependency> =
    findPlugin(name)
        .orElseThrow { NoSuchElementException("Plugin $name not found in version catalog") }

private fun VersionCatalog.findPluginOrThrow(name: String): PluginDependency =
    findPluginProviderOrThrow(name)
        .get()

private fun VersionCatalog.findLibraryOrThrow(name: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(name)
        .orElseThrow { NoSuchElementException("Library $name not found in version catalog") }

private fun VersionCatalog.findVersionOrThrow(name: String): String =
    findVersion(name)
        .orElseThrow { NoSuchElementException("Version $name not found in version catalog") }
        .requiredVersion
