//
//  ProblemView.swift
//  iosApp
//
//  Created by va.petrov on 24.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Combine
import UmbrellaIos

struct SolutionsView: View {
    private let component: SolutionComponent
    
    @StateValue
    private var state: SolutionState
    private var labels: AnyPublisher<SolutionLabel, Never>
    
    @State private var alertIsPresented = false
    @State private var alertMessage = ""
    
    init(component: SolutionComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
        labels = component.labels.asPublisher() as AnyPublisher<SolutionLabel, Never>
    }
    
    var body: some View {
        SolutionContent(
            state: state,
            onAddSolutionClick: {
                component.accept(intent: SolutionIntent.AddNewSolution())
            },
            onSuggestSolutionClick: {
                component.accept(intent: SolutionIntent.SuggestNewSolution())
            },
            onSelectSolution: { id in
                component.accept(intent: SolutionIntent.SelectSolution(id: id))
            },
            onDeleteSolution: { id in
                component.accept(intent: SolutionIntent.DeleteSolution(id: id))
            },
            onChangeSolutionDescription: { id, description in
                component.accept(intent: SolutionIntent.ChangeSolutionDescription(id: id, description: description))
            },
            onGoToProblem: {
                component.accept(intent: SolutionIntent.GoToProblem())
            },
            onGoToDecision: {
                component.accept(intent: SolutionIntent.GoToDecision())
            }
        )
            .onReceive(labels) { label in
                switch label {
                case let onAddNewSolutionFailure as SolutionLabel.OnAddNewSolutionFailure:
                    alertIsPresented = true
                    alertMessage = onAddNewSolutionFailure.message
                default: break
                }
            }
            .alert(isPresented: $alertIsPresented) {
                Alert(
                    title: Text("Error"),
                    message: Text(alertMessage),
                    dismissButton: .default(Text("OK"))
                )
            }
    }
}

private struct SolutionContent: View {
    let state: SolutionState
    let onAddSolutionClick: () -> Void
    let onSuggestSolutionClick: () -> Void
    let onSelectSolution: (Int32) -> Void
    let onDeleteSolution: (Int32) -> Void
    let onChangeSolutionDescription: (Int32, String) -> Void
    let onGoToProblem: () -> Void
    let onGoToDecision: () -> Void
    
    @ScaledMetric private var spacing = 16
    
    @State private var showSnackbar: Bool = false
    @State private var snackbarMessage: String = ""
    
    var body: some View {
        VStack {
            ScrollView {
                VStack(spacing: spacing) {
                    Button(action: onAddSolutionClick) {
                        Text("Add solution")
                            .frame(maxWidth: .infinity)
                    }
                        .buttonStyle(.borderedProminent)
                    Button(action: onSuggestSolutionClick) {
                        Text("Suggest solution")
                            .frame(maxWidth: .infinity)
                    }
                        .disabled(!state.isSuggestSolutionEnabled)
                        .buttonStyle(.borderedProminent)
                    ForEach(Array(state.solutions.enumerated()), id: \.element.id) { _, solution in
                        SolutionRow(
                            description: solution.description_,
                            isSelected: solution.isSelected,
                            onSelect: {
                                onSelectSolution(Int32(solution.id))
                            },
                            onDelete: {
                                onDeleteSolution(Int32(solution.id))
                            },
                            onDescriptionChange: { description in
                                onChangeSolutionDescription(Int32(solution.id), description)
                            }
                        )
                    }
                    HStack(spacing: spacing) {
                        Button(action: onGoToProblem) {
                            Text("To problem")
                                .frame(maxWidth: .infinity)
                        }
                            .disabled(!state.isGoToProblemEnabled)
                            .buttonStyle(.bordered)
                        Button(action: onGoToDecision) {
                            Text("To decision")
                                .frame(maxWidth: .infinity)
                        }
                            .disabled(!state.isGoToDecisionEnabled)
                            .buttonStyle(.borderedProminent)
                    }
                }
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

private struct SolutionRow: View {
    let description: String
    let isSelected: Bool
    let onSelect: () -> Void
    let onDelete: () -> Void
    let onDescriptionChange: (String) -> Void
    
    var body: some View {
        HStack {
            RadioButton(isSelected: isSelected, onClick: onSelect)
            TextField(
                "Solution", 
                text: Binding(get: { description }, set: onDescriptionChange)
            )
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

