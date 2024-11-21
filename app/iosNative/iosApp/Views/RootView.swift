import SwiftUI
import UmbrellaIos

struct RootView: View {
    let component: TabsComponent
    
    @StateValue
    private var tabs: ChildStack<AnyObject, TabsComponentChild>
    
    private var activeChild: TabsComponentChild { tabs.active.instance }
    
    init(component: TabsComponent) {
        self.component = component
        _tabs = StateValue(component.tabs)
    }
    
    var body: some View {
        VStack {
            ChildView(child: activeChild)
                .frame(maxHeight: .infinity)
                .padding()
            Divider()
            HStack(alignment: .center) {
                Button(action: component.onMakeDecisionItemClicked) {
                    Label("Make decision", systemImage: "checkmark.circle.fill")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is TabsComponentChild.MakeDecision ? 1 : 0.5)
                }
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                Button(action: component.onHistoryItemClicked) {
                    Label("History", systemImage: "list.bullet")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is TabsComponentChild.History ? 1 : 0.5)
                }
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
            }
            .padding(.vertical, 4)
        }
    }
}

private struct ChildView: View {
    let child: TabsComponentChild
    
    var body: some View {
        switch child {
        case let child as TabsComponentChild.MakeDecision: MakeDecisionView(component: child.component)
        case let child as TabsComponentChild.History: HistoryView(component: child.component)
        default: EmptyView()
        }
    }
}

private struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 8) {
            configuration.icon
            configuration.title
        }
    }
}
