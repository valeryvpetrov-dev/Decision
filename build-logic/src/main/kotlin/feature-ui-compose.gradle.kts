import ext.compose
import ext.debugImplementation
import ext.versionCatalog
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.android.build.gradle.LibraryExtension as AndroidLibraryExtension

with(plugins) {
    apply(project.versionCatalog.plugins.buildLogicCommonKmp.get().pluginId)
    apply(project.versionCatalog.plugins.jetbrainsCompose.get().pluginId)
    apply(project.versionCatalog.plugins.composeCompiler.get().pluginId)
}

extensions.configure<KotlinMultiplatformExtension> {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(versionCatalog.libs.androidxActivityCompose)
            implementation(versionCatalog.libs.koinAndroid)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

extensions.configure<AndroidLibraryExtension> {
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
