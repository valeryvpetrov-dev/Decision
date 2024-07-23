package dev.valeryvpetrov.decision.feature.decision.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

class Bootstrapper : CoroutineBootstrapper<Action>() {
    override fun invoke() {
        dispatch(Action.CalculateDecision)
    }
}