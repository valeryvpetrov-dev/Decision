package decision.decision.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.State
import decision.decision.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

typealias RealDecisionComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
    storeFactory: StoreFactory,
) : ComponentContext by componentContext, Component, KoinComponent {

    private val store: Store<Intent, State, Label> = instanceKeeper.getStore {
        storeFactory.create(stateKeeper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToSolutions() = onGoToSolutions.invoke()

    override fun onRestart() = onRestart.invoke()
}