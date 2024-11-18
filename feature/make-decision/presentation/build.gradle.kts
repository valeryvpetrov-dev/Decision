plugins {
    alias(libs.plugins.build.logic.feature.presentation)
    alias(libs.plugins.icerock.multiplatform.resources)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.api)
            api(projects.feature.problem.presentation)
            api(projects.feature.solution.presentation)
            api(projects.feature.decision.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.presentation"
}