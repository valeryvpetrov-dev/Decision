package decision.decision.component

import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.State

typealias DecisionComponent = Component

interface Component : mvi.Component<State, Label, Intent> {

    fun onGoToSolutions()
    fun onRestart()
}