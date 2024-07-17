package decision.solutions.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface StoreFactory {

    fun create(): Store<Intent, State, Label>
}