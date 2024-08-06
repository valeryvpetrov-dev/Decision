package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.feature.chat_gpt.api.ChatGptRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase
import dev.valeryvpetrov.decision.feature.solution.impl.SuggestSolutionUseCaseImpl
import org.koin.dsl.module

val implModule = module {
    factory<SuggestSolutionUseCase> {
        SuggestSolutionUseCaseImpl(
            decisionRepository = get<DecisionRepository>(),
            chatGptRepository = get<ChatGptRepository>(),
        )
    }
}