package decision.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import decision.decision.component.DecisionComponent
import decision.problem.component.ProblemComponent
import decision.solutions.component.SolutionsComponent

interface Component {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Problem(val component: ProblemComponent) : Child()
        class Solutions(val component: SolutionsComponent) : Child()
        class Decision(val component: DecisionComponent) : Child()
    }
}