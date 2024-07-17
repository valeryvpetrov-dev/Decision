package decision.solutions.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import decision.solutions.mvi.Intent
import decision.solutions.mvi.Label
import decision.solutions.mvi.State
import decision.solutions.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias RealSolutionsComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToProblem: () -> Unit,
    private val onGoToDecision: () -> Unit,
    storeFactory: StoreFactory,
) : ComponentContext by componentContext, Component {

    private val store: Store<Intent, State, Label> = instanceKeeper.getStore {
        storeFactory.create()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToProblem() = onGoToProblem.invoke()

    override fun onGoToDecision() = onGoToDecision.invoke()
}