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
        ScreenContent(
            state: state,
            onGoToSolutions: {
                component.accept(intent: DecisionIntent.GoToSolutions())
            },
            onRestart: {
                component.accept(intent: DecisionIntent.Restart())
            }
        )
    }
}

struct ScreenContent: View {
    let state: DecisionState
    let onGoToSolutions: () -> Void
    let onRestart: () -> Void
    
    var body: some View {
        VStack(spacing: 8) {
            ScrollView {
                VStack(spacing: 8) {
                    Text(state.decisionMessage)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.bottom, 8)
                    
                    HStack(spacing: 8) {
                        Button(action: onGoToSolutions) {
                            Text("To solutions")
                                .frame(maxWidth: .infinity)
                        }
                        .disabled(!state.isGoToSolutionsEnabled)
                        
                        Button(action: onRestart) {
                            Text("Restart")
                                .frame(maxWidth: .infinity)
                        }
                        .disabled(!state.isRestartEnabled)
                    }
                }
                .padding(16)
            }
        }
        .padding()
    }
}
