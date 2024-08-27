//
//  ProblemView.swift
//  iosApp
//
//  Created by va.petrov on 24.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import UmbrellaIos

struct SolutionsView: View {
    private let component: SolutionComponent
    
    @StateValue
    private var state: SolutionState
    
    @State private var showSnackbar: Bool = false
    @State private var snackbarMessage: String = ""
    
    init(component: SolutionComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
    }
    
    var body: some View {
        VStack(spacing: 16) {
            ScrollView {
                VStack(spacing: 8) {
                    AddSolutionButton {
                        component.accept(intent: SolutionIntent.AddNewSolution())
                    }
                    
                    SuggestSolutionButton(isEnabled: state.isSuggestSolutionEnabled) {
                        component.accept(intent: SolutionIntent.SuggestNewSolution())
                    }
                    
                    ForEach(state.solutions.indices, id: \.self) { index in
                        let solution = state.solutions[index]
                        SolutionRow(
                            text: solution.description_,
                            isSelected: solution.isSelected,
                            onSelect: {
                                component.accept(intent: SolutionIntent.SelectSolution(index: Int32(index)))
                            },
                            onDelete: {
                                component.accept(intent: SolutionIntent.DeleteSolution(index: Int32(index)))
                            },
                            onTextChange: { text in
                                component.accept(intent: SolutionIntent.ChangeSolutionDescription(index: Int32(index), description: text))
                            }
                        )
                    }
                    
                    HStack(spacing: 8) {
                        Button("To problem") {
                            component.accept(intent: SolutionIntent.GoToProblem())
                        }
                        .disabled(!state.isGoToProblemEnabled)
                        .frame(maxWidth: .infinity)
                        
                        Button("To decision") {
                            component.accept(intent: SolutionIntent.GoToDecision())
                        }
                        .disabled(!state.isGoToDecisionEnabled)
                        .frame(maxWidth: .infinity)
                    }
                }
                .padding(16)
            }
        }
        .snackbar(show: $showSnackbar, message: snackbarMessage)
        // FIXME: how to collect Flow?
//        .onReceive(component.labels.asPublisher()) { label in
//            if let failureLabel = label as? SolutionLabel.OnAddNewSolutionFailure {
//                snackbarMessage = failureLabel.message
//                showSnackbar = true
//            }
//        }
    }
}

private struct AddSolutionButton: View {
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Text("Add solution")
                .frame(maxWidth: .infinity)
        }
    }
}

private struct SuggestSolutionButton: View {
    let isEnabled: Bool
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Text("Suggest solution")
                .frame(maxWidth: .infinity)
        }
        .disabled(!isEnabled)
    }
}

private struct SolutionRow: View {
    let text: String
    let isSelected: Bool
    let onSelect: () -> Void
    let onDelete: () -> Void
    let onTextChange: (String) -> Void
    
    var body: some View {
        HStack {
            RadioButton(isSelected: isSelected, onClick: onSelect)
            TextField("Solution", text: Binding(get: { text }, set: onTextChange))
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .frame(maxWidth: .infinity)
            Button(action: onDelete) {
                Image(systemName: "xmark")
                    .foregroundColor(.black)
            }
        }
    }
}

private struct RadioButton: View {
    let isSelected: Bool
    let onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Image(systemName: isSelected ? "largecircle.fill.circle" : "circle")
                .foregroundColor(isSelected ? .blue : .gray)
        }
    }
}

private extension View {
    func snackbar(show: Binding<Bool>, message: String) -> some View {
        self.overlay(
            VStack {
                if show.wrappedValue {
                    Text(message)
                        .padding()
                        .background(Color.black.opacity(0.8))
                        .cornerRadius(8)
                        .foregroundColor(.white)
                        .transition(.move(edge: .bottom))
                        .animation(.easeInOut, value: show.wrappedValue)
                }
                Spacer()
            }
        )
    }
}

