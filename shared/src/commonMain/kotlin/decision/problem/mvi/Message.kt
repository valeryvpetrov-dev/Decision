package decision.problem.mvi

import model.Problem

sealed class Message {

    data class OnRefresh(val problem: Problem?) : Message()

    data class OnChangeProblemDescription(val description: String) : Message()
}