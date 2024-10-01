plugins {
    alias(libs.plugins.build.logic.feature.di)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.chatGpt.api)
            implementation(projects.feature.chatGpt.impl)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.chat_gpt.di"
}