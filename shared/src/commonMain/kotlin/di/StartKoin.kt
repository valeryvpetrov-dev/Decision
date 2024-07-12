package di

import decision.di.featureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun startKoinWithSharedInitialized(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(featureModule)
    }
}