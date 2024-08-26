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
    }
}

private struct ProblemContent: View {
    let state: ProblemState
    let onChangeProblemDescription: (String) -> Void
    let onGoToSolutionsClick: () -> Void
    
    var body: some View {
        VStack(spacing: 8) {
            ProblemTextField(
                value: state.description,
                onValueChange: onChangeProblemDescription
            )
            Button(
                action: onGoToSolutionsClick,
                label: {
                    Text("To solutions")
                }
            )
            .disabled(!state.isGoToSolutionsEnabled)
        }
        .padding(16)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
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
        .padding()
    }
}
