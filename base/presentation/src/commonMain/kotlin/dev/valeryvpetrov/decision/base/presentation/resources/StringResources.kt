package dev.valeryvpetrov.decision.base.presentation.resources

import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

interface StringResources {
    fun getString(resource: StringResource, vararg args: Any): String
    fun getString(resource: PluralsResource, quantity: Int): String
}
