plugins {
    alias(libs.plugins.decision.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.problem.ui.compose"
}