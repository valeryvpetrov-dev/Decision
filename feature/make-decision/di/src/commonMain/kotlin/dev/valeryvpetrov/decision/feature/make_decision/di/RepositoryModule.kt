package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.feature.make_decision.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.impl.DecisionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DecisionRepository> {
        DecisionRepositoryImpl()
    }
}