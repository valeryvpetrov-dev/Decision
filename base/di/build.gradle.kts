plugins {
    alias(libs.plugins.build.logic.common.kmp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.base.di"
}
