package dev.valeryvpetrov.decision.base.api

interface Provider<T> {

    fun get(): T
}