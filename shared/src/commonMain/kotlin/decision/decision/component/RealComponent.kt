package decision.decision.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.State
import decision.decision.mvi.Store
import decision.decision.mvi.StoreFactory
import decision.repository.DecisionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias RealDecisionComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
    // FIXME DI
    private val decisionRepository: DecisionRepository,
    private val store: Store = StoreFactory(DefaultStoreFactory()).create(decisionRepository)
) : ComponentContext by componentContext, Component {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToSolutions() = onGoToSolutions.invoke()

    override fun onRestart() = onRestart.invoke()

    init {
        lifecycle.subscribe(
            onStart = {
                store.accept(Intent.Refresh)
            },
        )
    }
}