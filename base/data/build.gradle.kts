plugins {
    alias(libs.plugins.decision.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.data"
}