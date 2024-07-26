package dev.valeryvpetrov.decision.feature.problem.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.Executor
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.Reducer
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Problem as QualifierProblem

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = QualifierProblem.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = QualifierProblem.StoreName.qualifier),
            executorFactory = get<Executor.Factory>(),
            reducer = get<Reducer>(),
        )
    }
    factory<String>(qualifier = QualifierProblem.StoreName.qualifier) {
        QualifierProblem.StoreName.name
    }
    factory<Executor.Factory> {
        object : Executor.Factory {
            override fun create(onGoToSolutions: (Problem) -> Unit) = Executor(
                onGoToSolutions = onGoToSolutions,
            )
        }
    }
    factoryOf(::Reducer)
}