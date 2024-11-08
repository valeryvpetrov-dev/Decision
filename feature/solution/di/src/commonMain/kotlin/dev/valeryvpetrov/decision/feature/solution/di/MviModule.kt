package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SolutionRepository
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Executor
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Reducer
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Solution as QualifierSolution

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = QualifierSolution.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = QualifierSolution.StoreName.qualifier),
            executorFactory = get<Executor.Factory>(),
            reducer = get<Reducer>(),
        )
    }
    factory<String>(qualifier = QualifierSolution.StoreName.qualifier) {
        QualifierSolution.StoreName.name
    }
    factory<Executor.Factory> {
        object : Executor.Factory {
            override fun create(
                onBackToProblem: (List<Solution>) -> Unit,
                onGoToDecision: (List<Solution>) -> Unit,
            ) = Executor(
                onBackToProblem = onBackToProblem,
                onGoToDecision = onGoToDecision,
                suggestSolutionUseCase = get<SuggestSolutionUseCase>(),
                solutionRepository = get<SolutionRepository>(),
            )
        }
    }
    factoryOf(::Reducer)
}