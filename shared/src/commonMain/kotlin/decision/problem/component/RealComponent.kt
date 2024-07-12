package decision.problem.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import decision.problem.mvi.Intent
import decision.problem.mvi.Label
import decision.problem.mvi.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias RealProblemComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    private val store: Store<Intent, State, Label>,
) : ComponentContext by componentContext, Component {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToSolutions() = onGoToSolutions.invoke()

    init {
        lifecycle.subscribe(
            onStart = {
                store.accept(Intent.Refresh)
            },
        )
    }
}