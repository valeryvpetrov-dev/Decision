package decision.problem.mvi

import decision.repository.DecisionRepository
import com.arkivanov.mvikotlin.core.store.Store as MviStore
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory


interface Store : MviStore<Intent, State, Label>

class StoreFactory(
    private val storeFactory: MviStoreFactory,
) {

    fun create(decisionRepository: DecisionRepository): Store =
        object : Store, MviStore<Intent, State, Label> by storeFactory.create(
            name = "Store",
            initialState = State.initial(),
            executorFactory = { Executor(decisionRepository) },
            reducer = Reducer()
        ) {}
}