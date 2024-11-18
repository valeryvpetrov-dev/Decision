package dev.valeryvpetrov.decision.umbrella.di

import dev.valeryvpetrov.decision.base.presentation.resources.IosStringResources
import dev.valeryvpetrov.decision.base.presentation.resources.StringResources
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<StringResources> {
        IosStringResources()
    }
}