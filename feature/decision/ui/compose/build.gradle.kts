plugins {
    alias(libs.plugins.decision.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.decision.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.decision.ui.compose"
}