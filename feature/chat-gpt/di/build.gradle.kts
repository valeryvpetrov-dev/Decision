plugins {
    alias(libs.plugins.decision.feature.di)
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