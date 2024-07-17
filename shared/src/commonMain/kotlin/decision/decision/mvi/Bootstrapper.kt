package decision.decision.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

class Bootstrapper : CoroutineBootstrapper<Action>() {
    override fun invoke() {
        dispatch(Action.CalculateDecision)
    }
}