package decision.di

import decision.repository.DecisionRepository
import decision.repository.DecisionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DecisionRepository> {
        DecisionRepositoryImpl()
    }
}