import ext.projects
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

with(plugins) {
    apply(project.versionCatalog.plugins.decisionCommonKmp.get().pluginId)
    apply(project.versionCatalog.plugins.kotlinSerialization.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    sourceSets {
        commonMain.dependencies {
            api(projects.base.api)
            implementation(versionCatalog.libs.kotlinxCoroutinesCore)
            implementation(versionCatalog.libs.kotlinxSerializationCore)
        }
    }
}
