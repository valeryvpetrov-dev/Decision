package decision.solutions.component

import decision.solutions.mvi.Intent
import decision.solutions.mvi.Label
import decision.solutions.mvi.State

typealias SolutionsComponent = Component

interface Component : mvi.Component<State, Label, Intent> {

    fun onGoToProblem()
    fun onGoToDecision()
}