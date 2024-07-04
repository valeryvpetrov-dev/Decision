package mvi.decision

import com.arkivanov.mvikotlin.core.store.create
import model.Problem
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory
import com.arkivanov.mvikotlin.core.store.Store as MviStore

interface Store : MviStore<Intent, State, Nothing>

class StoreFactory(
    private val storeFactory: MviStoreFactory
) {

    fun create(): Store = object : Store, MviStore<Intent, State, Nothing> by storeFactory.create(
        name = "Store",
        initialState = State.initial(),
        reducer = Reducer()
    ) {}
}