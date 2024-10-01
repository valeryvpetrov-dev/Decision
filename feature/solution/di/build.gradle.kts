plugins {
    alias(libs.plugins.decision.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.solution.api)
            implementation(projects.feature.solution.presentation)
            implementation(projects.feature.solution.impl)
            implementation(projects.feature.chatGpt.api)
            implementation(projects.feature.makeDecision.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.di"
}
