package dev.valeryvpetrov.decision.data.di

import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.data.impl.DecisionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DecisionRepository> {
        DecisionRepositoryImpl()
    }
}