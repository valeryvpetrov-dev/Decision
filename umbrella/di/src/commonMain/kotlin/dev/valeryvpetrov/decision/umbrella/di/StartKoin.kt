package dev.valeryvpetrov.decision.umbrella.di

import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import dev.valeryvpetrov.decision.feature.chat_gpt.di.featureModule as chatGptFeatureModule
import dev.valeryvpetrov.decision.feature.make_decision.di.featureModule as makeDecisionFeatureModule
import org.koin.core.context.startKoin as srcStartKoin

fun startKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return srcStartKoin {
        appDeclaration()
        modules(
            makeDecisionFeatureModule,
            chatGptFeatureModule,
            mviModule,
            platformModule()
        )
    }
}