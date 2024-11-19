package dev.valeryvpetrov.decision.feature.hello_world.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.presentation.resources.StringResources
import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponentImpl
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldStoreFactory
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val presentationModule = module {
    factory<HelloWorldComponent.Factory> {
        HelloWorldComponentImpl.Factory(
            storeFactoryProvider = get<Provider<HelloWorldStoreFactory>>(),
            stringResources = get<StringResources>(),
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
            greetingUseCase = get<GreetingUseCase>(),
        )
    }
    factory<String>(qualifier = qualifier(Qualifier.STORE_NAME)) {
        "HelloWorldStore"
    }
}