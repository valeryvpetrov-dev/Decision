//
//  ProblemView.swift
//  iosApp
//
//  Created by va.petrov on 24.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import UmbrellaIos

struct ProblemView: View {
    private let component: ProblemComponent
    
    @StateValue
    private var state: ProblemState
    
    init(component: ProblemComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
    }
    
    var body: some View {
        ProblemContent(
            state: state,
            onChangeProblemDescription: { description in
                component.accept(intent: ProblemIntent.ChangeProblemDescription(description: description))
            },
            onGoToSolutionsClick: {
                component.accept(intent: ProblemIntent.GoToSolutions.shared)
            }
        )
            .padding()
    }
}

private struct ProblemContent: View {
    let state: ProblemState
    let onChangeProblemDescription: (String) -> Void
    let onGoToSolutionsClick: () -> Void
    
    @ScaledMetric private var stackSpacing = 16
    
    var body: some View {
        VStack(spacing: stackSpacing) {
            ProblemTextField(
                value: state.description_,
                onValueChange: onChangeProblemDescription
            )
            Button(
                action: onGoToSolutionsClick,
                label: {
                    Text("To solutions")
                        .frame(maxWidth: .infinity)
                }
            )
            .disabled(!state.isGoToSolutionsEnabled)
            .buttonStyle(.borderedProminent)
            Spacer()
        }
    }
}

private struct ProblemTextField: View {
    let value: String
    let onValueChange: (String) -> Void
    
    var body: some View {
        TextField(
            "Problem",
            text: Binding(
                get: { value },
                set: { newValue in onValueChange(newValue) }
            )
        )
        .textFieldStyle(RoundedBorderTextFieldStyle())
    }
}
