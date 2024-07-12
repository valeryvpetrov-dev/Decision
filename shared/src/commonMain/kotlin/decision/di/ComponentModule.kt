package decision.di

import com.arkivanov.decompose.ComponentContext
import decision.component.Component
import decision.component.RealComponent
import org.koin.dsl.module

val componentModule = module {
    factory<Component> { (componentContext: ComponentContext) ->
        RealComponent(
            componentContext = componentContext,
        )
    }
}