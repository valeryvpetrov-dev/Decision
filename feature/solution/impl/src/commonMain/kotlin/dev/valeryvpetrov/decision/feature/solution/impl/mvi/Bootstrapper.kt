package dev.valeryvpetrov.decision.feature.solution.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

class Bootstrapper : CoroutineBootstrapper<Action>() {
    override fun invoke() {
        dispatch(Action.RestoreState)
    }
}