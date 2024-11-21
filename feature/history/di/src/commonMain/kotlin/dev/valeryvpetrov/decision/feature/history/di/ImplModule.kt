package dev.valeryvpetrov.decision.feature.history.di

import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository
import dev.valeryvpetrov.decision.feature.history.impl.repository.HistoryRepositoryImpl
import org.koin.dsl.module

val implModule = module {
    single<HistoryRepository> {
        HistoryRepositoryImpl()
    }
}