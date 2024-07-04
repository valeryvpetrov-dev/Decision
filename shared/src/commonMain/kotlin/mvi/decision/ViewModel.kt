package mvi.decision

import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel as AndroidXViewModel

class ViewModel : AndroidXViewModel() {

    private val store: Store = StoreFactory(TimeTravelStoreFactory()).create()

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<State> get() = store.stateFlow

    fun accept(intent: Intent): Unit = store.accept(intent)

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }
}