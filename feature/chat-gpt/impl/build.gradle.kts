import java.util.Properties

plugins {
    alias(libs.plugins.decision.feature.impl)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.buildconfig)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.chatGpt.api)
            api(libs.bundles.ktor)
            api(libs.kotlinx.serialization.json)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }
    }
}

android {
    namespace = "dev.valeryvpetrov.decision.feature.chat_gpt.impl"
}

buildConfig {
    packageName("dev.valeryvpetrov.decision.feature.chat_gpt.impl")

    val localProperties = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    buildConfigField("CHAT_GPT_API_TOKEN", localProperties.getProperty("CHAT_GPT_API_TOKEN"))
}