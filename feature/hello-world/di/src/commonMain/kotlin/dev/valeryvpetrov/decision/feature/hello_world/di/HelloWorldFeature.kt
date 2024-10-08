package dev.valeryvpetrov.decision.feature.hello_world.di

import org.koin.dsl.module

val helloWorldFeature = module {
    includes(
        implModule,
        presentationModule,
    )
}