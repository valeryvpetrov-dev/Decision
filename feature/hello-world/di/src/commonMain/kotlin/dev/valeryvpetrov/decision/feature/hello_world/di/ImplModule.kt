package dev.valeryvpetrov.decision.feature.hello_world.di

import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import dev.valeryvpetrov.decision.feature.hello_world.impl.GreetingUseCaseImpl
import org.koin.dsl.module

val implModule = module {
    single<GreetingUseCase> {
        GreetingUseCaseImpl()
    }
}