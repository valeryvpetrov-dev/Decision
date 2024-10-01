plugins {
    alias(libs.plugins.build.logic.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.decision.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.decision.presentation"
}