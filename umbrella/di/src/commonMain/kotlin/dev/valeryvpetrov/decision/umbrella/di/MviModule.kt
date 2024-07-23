package dev.valeryvpetrov.decision.umbrella.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.dsl.module

val mviModule = module {
    single<StoreFactory> {
        DefaultStoreFactory()
    }
}