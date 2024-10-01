plugins {
    alias(libs.plugins.build.logic.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.impl"
}