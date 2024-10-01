import ext.projects
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

with(plugins) {
    apply(project.versionCatalog.plugins.buildLogicCommonKmp.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    sourceSets {
        commonMain.dependencies {
            api(projects.base.impl)
            implementation(versionCatalog.libs.kotlinxCoroutinesCore)
        }
        androidMain.dependencies {
            implementation(versionCatalog.libs.kotlinxCoroutinesAndroid)
        }
        jvmMain.dependencies {
            implementation(versionCatalog.libs.kotlinxCoroutinesSwing)
        }
    }
}