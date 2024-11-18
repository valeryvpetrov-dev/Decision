package dev.valeryvpetrov.decision.base.presentation.resources

import android.content.Context
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.PluralStringDesc
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc

class AndroidStringResources(private val context: Context) : StringResources {

    override fun getString(resource: StringResource, vararg args: Any): String {
        val formattedDesc = StringDesc.ResourceFormatted(resource, *args)
        return formattedDesc.toString(context)
    }

    override fun getString(resource: PluralsResource, quantity: Int): String {
        return PluralStringDesc(resource, quantity).toString(context)
    }
}
