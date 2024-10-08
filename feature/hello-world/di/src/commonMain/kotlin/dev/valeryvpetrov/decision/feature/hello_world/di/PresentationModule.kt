package dev.valeryvpetrov.decision.feature.hello_world.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponentImpl
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldExecutor
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldReducer
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldStoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val presentationModule = module {
    factory<HelloWorldComponent.Factory> {
        HelloWorldComponentImpl.Factory(
            storeFactoryProvider = get<Provider<HelloWorldStoreFactory>>(),
        )
    }
    factory<Provider<HelloWorldStoreFactory>> {
        object : Provider<HelloWorldStoreFactory> {
            override fun get(): HelloWorldStoreFactory = get<HelloWorldStoreFactory>()
        }
    }
    factory<HelloWorldStoreFactory> {
        HelloWorldStoreFactory(
            storeName = get<String>(qualifier = qualifier(Qualifier.STORE_NAME)),
            storeFactory = get<MviStoreFactory>(),
            executor = get<HelloWorldExecutor>(),
            reducer = get<HelloWorldReducer>(),
        )
    }
    factory<HelloWorldExecutor> {
        HelloWorldExecutor(
            greetingUseCase = get<GreetingUseCase>(),
        )
    }
    factoryOf(::HelloWorldReducer)
    factory<String>(qualifier = qualifier(Qualifier.STORE_NAME)) {
        "HelloWorldStore"
    }
}