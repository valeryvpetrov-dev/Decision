package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

class Bootstrapper : CoroutineBootstrapper<Action>() {
    override fun invoke() {
        dispatch(Action.Restore)
    }
}