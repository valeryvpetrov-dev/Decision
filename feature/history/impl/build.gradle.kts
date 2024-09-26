plugins {
    alias(libs.plugins.decision.feature.impl)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.history.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.history.impl"
}