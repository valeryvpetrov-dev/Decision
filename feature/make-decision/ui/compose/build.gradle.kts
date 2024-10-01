plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.makeDecision.api)
            implementation(projects.feature.makeDecision.presentation)
            implementation(projects.feature.problem.ui.compose)
            implementation(projects.feature.solution.ui.compose)
            implementation(projects.feature.decision.ui.compose)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.make_decision.ui.compose"
}