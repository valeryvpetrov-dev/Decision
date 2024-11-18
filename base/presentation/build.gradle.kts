plugins {
    alias(libs.plugins.build.logic.common.kmp)
    alias(libs.plugins.icerock.multiplatform.resources)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base.api)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.decompose)
            api(libs.bundles.mvikotlin)
            api(libs.mvikotlin.extensions.coroutines)
            api(libs.icerock.multiplatform.resources)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.presentation"
}
