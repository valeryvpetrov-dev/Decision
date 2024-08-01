plugins {
    alias(libs.plugins.decision.feature.impl)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.problem.impl"
}