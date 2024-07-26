package dev.valeryvpetrov.decision.umbrella.di

import dev.valeryvpetrov.decision.feature.make_decision.di.featureModule
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin as srcStartKoin

fun startKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return srcStartKoin {
        appDeclaration()
        modules(
            featureModule,
            mviModule,
        )
    }
}