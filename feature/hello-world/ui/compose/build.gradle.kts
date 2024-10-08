plugins {
    alias(libs.plugins.build.logic.feature.ui.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.helloWorld.presentation)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.ui.compose"
}
