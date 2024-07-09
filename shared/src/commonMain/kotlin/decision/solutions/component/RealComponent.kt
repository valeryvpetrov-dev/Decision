package decision.solutions.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.repository.DecisionRepository
import decision.solutions.mvi.Intent
import decision.solutions.mvi.Label
import decision.solutions.mvi.State
import decision.solutions.mvi.Store
import decision.solutions.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias RealSolutionsComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToProblem: () -> Unit,
    private val onGoToDecision: () -> Unit,
    // FIXME DI
    private val decisionRepository: DecisionRepository,
    private val store: Store = StoreFactory(DefaultStoreFactory()).create(decisionRepository)
) : ComponentContext by componentContext, Component {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToProblem() = onGoToProblem.invoke()

    override fun onGoToDecision() = onGoToDecision.invoke()

    init {
        lifecycle.subscribe(
            onStart = {
                store.accept(Intent.Refresh)
            },
        )
    }
}