plugins {
    alias(libs.plugins.decision.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.solution.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.presentation"
}