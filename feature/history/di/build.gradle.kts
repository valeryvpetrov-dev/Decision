plugins {
    alias(libs.plugins.decision.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.history.api)
            implementation(projects.feature.history.impl)
            implementation(projects.feature.history.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.history.di"
}
