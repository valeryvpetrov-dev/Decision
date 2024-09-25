package dev.valeryvpetrov.decision.feature.chat_gpt.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.chat_gpt.api.repository.ChatGptRepository
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.ChatGptApiTokenProvider
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.ChatGptRepositoryImpl
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.api.ChatGptApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val featureModule = module {
    single<ChatGptRepository> {
        ChatGptRepositoryImpl(
            chatGptApi = get<ChatGptApi>()
        )
    }
    factory<ChatGptApi> {
        ChatGptApi(
            httpClient = get<HttpClient>(),
            chatGptModel = get<String>(qualifier = Qualifier.Feature.ChatGpt.Model.qualifier)
        )
    }
    factory<DefaultRequest> {
        DefaultRequest.prepare {
            url(get<String>(qualifier = Qualifier.Feature.ChatGpt.BaseUrl.qualifier))
        }
    }
    factory<ContentNegotiation> {
        ContentNegotiation.prepare {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    factory<Logging> {
        Logging.prepare {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
    factory<Provider<String>>(qualifier = Qualifier.Feature.ChatGpt.ApiTokenProvider.qualifier) {
        ChatGptApiTokenProvider()
    }
    factory<Auth> {
        val apiTokenProvider = get<Provider<String>>(
            qualifier = Qualifier.Feature.ChatGpt.ApiTokenProvider.qualifier
        )
        Auth.prepare {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = apiTokenProvider.get(),
                        refreshToken = "" // Nothing to provide
                    )
                }
            }
        }
    }
    factory<HttpClient> {
        HttpClient().apply {
            DefaultRequest.Plugin.install(
                plugin = get<DefaultRequest>(),
                scope = this,
            )
            ContentNegotiation.Plugin.install(
                plugin = get<ContentNegotiation>(),
                scope = this,
            )
            Logging.install(
                plugin = get<Logging>(),
                scope = this,
            )
            Auth.install(
                plugin = get<Auth>(),
                scope = this,
            )
        }
    }
    factory<String>(qualifier = Qualifier.Feature.ChatGpt.BaseUrl.qualifier) {
        "https://api.proxyapi.ru/openai/v1/"
    }
    factory<String>(qualifier = Qualifier.Feature.ChatGpt.Model.qualifier) {
        // Take into account pricing: https://proxyapi.ru/pricing
        "gpt-3.5-turbo-0125"
    }
}