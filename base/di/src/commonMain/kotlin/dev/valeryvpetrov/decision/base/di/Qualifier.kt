package dev.valeryvpetrov.decision.base.di

import org.koin.core.qualifier.qualifier
import org.koin.core.qualifier.Qualifier as KoinQualifier

@Suppress("ConvertObjectToDataObject")
sealed class Qualifier {

    val name: String
        get() = "${this::class.qualifiedName}"

    val qualifier: KoinQualifier
        get() = qualifier(this.name)

    sealed class Feature : Qualifier() {

        sealed class Problem : Feature() {

            object StoreName : Problem()
            object StoreFactoryProvider : Problem()
        }

        sealed class Solution : Feature() {

            object StoreName : Solution()
            object StoreFactoryProvider : Solution()
        }

        sealed class Decision : Feature() {

            object StoreName : Decision()
            object StoreFactoryProvider : Decision()
        }

        sealed class MakeDecision : Feature() {

            object StoreName : MakeDecision()
            object StoreFactoryProvider : MakeDecision()
        }
    }
}