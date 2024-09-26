package dev.valeryvpetrov.decision.feature.make_decision.impl

import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionUseCase

class MakeDecisionUseCaseImpl(
    private val makeDecisionRepository: MakeDecisionRepository,
    private val historyRepository: HistoryRepository,
) : MakeDecisionUseCase by makeDecisionRepository {

    override suspend fun finalizeDecision(): String {
        return makeDecisionRepository.finalizeDecision().apply {
            historyRepository.save(this)
        }
    }
}