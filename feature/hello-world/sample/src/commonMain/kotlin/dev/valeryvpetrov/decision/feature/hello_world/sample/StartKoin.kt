package dev.valeryvpetrov.decision.feature.hello_world.sample

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.valeryvpetrov.decision.feature.hello_world.di.helloWorldFeature
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.core.context.startKoin as srcStartKoin

val mviModule = module {
    single<StoreFactory> {
        LoggingStoreFactory(TimeTravelStoreFactory())
    }
}

fun startKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return srcStartKoin {
        appDeclaration()
        modules(
            helloWorldFeature,
            mviModule,
        )
    }
}