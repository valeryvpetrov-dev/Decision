package decision.problem.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.problem.mvi.Intent
import decision.problem.mvi.Label
import decision.problem.mvi.State
import decision.problem.mvi.Store
import decision.problem.mvi.StoreFactory
import decision.repository.DecisionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias RealProblemComponent = RealComponent

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    // FIXME DI
    private val decisionRepository: DecisionRepository,
    private val store: Store = StoreFactory(DefaultStoreFactory()).create(decisionRepository)
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