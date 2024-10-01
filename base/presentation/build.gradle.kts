plugins {
    alias(libs.plugins.build.logic.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base.api)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.decompose)
            api(libs.bundles.mvikotlin)
            api(libs.mvikotlin.extensions.coroutines)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.presentation"
}
