package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.impl.MakeDecisionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MakeDecisionRepository> {
        MakeDecisionRepositoryImpl()
    }
}