plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
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