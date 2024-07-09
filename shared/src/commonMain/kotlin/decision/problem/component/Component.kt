package decision.problem.component

import decision.problem.mvi.Intent
import decision.problem.mvi.Label
import decision.problem.mvi.State

typealias ProblemComponent = Component

interface Component : mvi.Component<State, Label, Intent> {

    fun onGoToSolutions()
}