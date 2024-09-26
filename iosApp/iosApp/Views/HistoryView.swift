import SwiftUI
import UmbrellaIos

struct HistoryView: View {
    
    private let component: HistoryComponent
    
    @StateValue
    private var state: HistoryState
    
    init(component: HistoryComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
    }
    
    var body: some View {
        HistoryContent(state: state)
            .padding()
            .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

private struct HistoryContent: View {
    let state: HistoryState
    
    var body: some View {
        switch state {
        case is HistoryState.Loading:
            EmptyView()
        case let contentState as HistoryState.Content:
            List {
                ForEach(Array(contentState.historyList.enumerated()), id: \.1.id) { index, item in
                    HistoryItem(
                        id: item.id,
                        decisionMessage: item.decisionMessage
                    )
                    .listRowInsets(EdgeInsets())
                }
            }
            .listStyle(PlainListStyle())
        case is HistoryState.NoContent:
            VStack {
                Text("History of made decisions will be displayed here.")
                    .font(.caption)
                    .multilineTextAlignment(.center)
                    .padding()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        case is HistoryState.Error:
            EmptyView()
        default:
            fatalError("Not implemented case")
        }
    }
}

private struct HistoryItem: View {
    let id: Int32
    let decisionMessage: String
    
    init(id: Int32, decisionMessage: String) {
        self.id = id
        self.decisionMessage = decisionMessage
    }
    
    var body: some View {
        Text("\(id): \(decisionMessage)")
            .padding(.vertical, 8)
    }
}
