plugins {
    alias(libs.plugins.build.logic.feature.impl)
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