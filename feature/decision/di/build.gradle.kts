plugins {
    alias(libs.plugins.build.logic.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.decision.api)
            implementation(projects.feature.decision.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.decision.di"
}