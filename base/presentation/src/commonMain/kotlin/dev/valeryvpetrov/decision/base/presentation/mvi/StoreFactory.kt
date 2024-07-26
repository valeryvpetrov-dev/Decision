package dev.valeryvpetrov.decision.base.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store

typealias BaseStoreFactory<Intent, State, Label> = StoreFactory<Intent, State, Label>

interface StoreFactory<in Intent : Any, out State : Any, out Label : Any> {

    fun create(stateKeeper: StateKeeper): Store<Intent, State, Label>
}