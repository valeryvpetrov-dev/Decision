import ext.projects
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

with(plugins) {
    apply(project.versionCatalog.plugins.kotlinMultiplatform.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.base.data)
            implementation(versionCatalog.libs.kotlinxCoroutinesCore)
        }
    }
}