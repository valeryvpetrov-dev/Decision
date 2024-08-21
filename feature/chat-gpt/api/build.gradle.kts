plugins {
    alias(libs.plugins.decision.feature.api)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.problem.api)
            implementation(projects.feature.solution.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.chat_gpt.api"
}