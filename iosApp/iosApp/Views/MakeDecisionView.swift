import SwiftUI
import UmbrellaIos

struct MakeDecisionView: View {
    let component: MakeDecisionComponent

    init(component: MakeDecisionComponent) {
        self.component = component
    }
    
    var body: some View {
        StackView(
            stackValue: StateValue(component.childStack),
            getTitle: {
                switch $0 {
                case is MakeDecisionComponent.ChildProblem: return "Problem"
                case is MakeDecisionComponent.ChildSolutions: return "Solutions"
                case is MakeDecisionComponent.ChildDecision: return "Decision"
                default: return ""
                }
            },
            onBack: component.onBack,
            childContent: {
                switch $0 {
                case let child as MakeDecisionComponent.ChildProblem: ProblemView(component: child.component)
                case let child as MakeDecisionComponent.ChildSolutions: SolutionsView(component: child.component)
                case let child as MakeDecisionComponent.ChildDecision: DecisionView(component: child.component)
                default: EmptyView()
                }
            }
        )
    }
}
