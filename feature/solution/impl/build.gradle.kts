plugins {
    alias(libs.plugins.build.logic.feature.impl)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.solution.api)
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.problem.api)
            implementation(projects.feature.chatGpt.api)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.solution.impl"
}