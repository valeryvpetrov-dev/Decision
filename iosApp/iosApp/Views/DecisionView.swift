//
//  ProblemView.swift
//  iosApp
//
//  Created by va.petrov on 24.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import UmbrellaIos

struct DecisionView: View {
    private let component: DecisionComponent
    
    @StateValue
    private var state: DecisionState
    
    init(component: DecisionComponent) {
        self.component = component
        _state = StateValue(component.stateValue)
    }
    
    var body: some View {
        DecisionContent(
            state: state,
            onGoToSolutions: {
                component.accept(intent: DecisionIntent.GoToSolutions())
            },
            onRestart: {
                component.accept(intent: DecisionIntent.Restart())
            }
        )
            .padding()
    }
}

struct DecisionContent: View {
    let state: DecisionState
    let onGoToSolutions: () -> Void
    let onRestart: () -> Void
    
    @ScaledMetric private var spacing = 16
    
    var body: some View {
        VStack {
            ScrollView {
                VStack(spacing: spacing) {
                    Text(state.decisionMessage)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    HStack(spacing: spacing) {
                        Button(action: onGoToSolutions) {
                            Text("To solutions")
                                .frame(maxWidth: .infinity)
                        }
                            .disabled(!state.isGoToSolutionsEnabled)
                            .buttonStyle(.bordered)
                        Button(action: onRestart) {
                            Text("Restart")
                                .frame(maxWidth: .infinity)
                        }
                            .disabled(!state.isRestartEnabled)
                            .buttonStyle(.borderedProminent)
                    }
                }
            }
        }
    }
}
