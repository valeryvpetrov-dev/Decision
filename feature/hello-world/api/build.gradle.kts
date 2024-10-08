plugins {
    alias(libs.plugins.build.logic.feature.api)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.hello_world.api"
}
