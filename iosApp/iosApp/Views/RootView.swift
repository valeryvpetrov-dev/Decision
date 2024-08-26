import UIKit
import SwiftUI
import UmbrellaIos

struct RootView: View {
    let root: MakeDecisionComponent

    init(root: MakeDecisionComponent) {
        self.root = root
    }
    
    var body: some View {
        // TODO: fix screen transition
        StackView(
            stackValue: StateValue(root.childStack),
            getTitle: {
                switch $0 {
                case is MakeDecisionComponent.ChildProblem: return "Problem"
                case is MakeDecisionComponent.ChildSolutions: return "Solutions"
                case is MakeDecisionComponent.ChildDecision: return "Decision"
                default: return ""
                }
            },
            onBack: root.onBack,
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
