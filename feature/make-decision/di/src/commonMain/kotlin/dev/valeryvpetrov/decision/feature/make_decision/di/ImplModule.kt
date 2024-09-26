package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionUseCase
import dev.valeryvpetrov.decision.feature.make_decision.impl.MakeDecisionRepositoryImpl
import dev.valeryvpetrov.decision.feature.make_decision.impl.MakeDecisionUseCaseImpl
import org.koin.dsl.module

val implModule = module {
    single<MakeDecisionUseCase> {
        MakeDecisionUseCaseImpl(
            makeDecisionRepository = get<MakeDecisionRepository>(),
            historyRepository = get<HistoryRepository>(),
        )
    }
    single<MakeDecisionRepository> {
        MakeDecisionRepositoryImpl()
    }
}