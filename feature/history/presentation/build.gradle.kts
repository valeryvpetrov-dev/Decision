plugins {
    alias(libs.plugins.decision.feature.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.history.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.history.presentation"
}
