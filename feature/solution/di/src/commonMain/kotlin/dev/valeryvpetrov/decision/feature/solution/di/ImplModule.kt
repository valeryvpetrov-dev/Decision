package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.feature.chat_gpt.api.repository.ChatGptRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase
import dev.valeryvpetrov.decision.feature.solution.impl.SuggestSolutionUseCaseImpl
import org.koin.dsl.module

val implModule = module {
    factory<SuggestSolutionUseCase> {
        SuggestSolutionUseCaseImpl(
            makeDecisionRepository = get<MakeDecisionRepository>(),
            chatGptRepository = get<ChatGptRepository>(),
        )
    }
}