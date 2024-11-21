package dev.valeryvpetrov.decision.umbrella.di

import dev.valeryvpetrov.decision.feature.tabs.di.tabsFeatureModule
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import dev.valeryvpetrov.decision.feature.chat_gpt.di.featureModule as chatGptFeatureModule
import org.koin.core.context.startKoin as srcStartKoin

fun startKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return srcStartKoin {
        appDeclaration()
        modules(
            tabsFeatureModule,
            chatGptFeatureModule,
            mviModule,
            platformModule()
        )
    }
}