import ext.projects
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

with(plugins) {
    apply(project.versionCatalog.plugins.decisionCommonKmp.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    sourceSets {
        commonMain.dependencies {
            api(projects.base.di)
            implementation(versionCatalog.libs.mvikotlin)
            implementation(versionCatalog.libs.koinCore)
        }
    }
}