package mvi.decision

import model.Problem
import model.Solution
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Intent> {

    override fun State.reduce(intent: Intent): State = when (intent) {
        is Intent.ChangeProblemDescription -> {
            when (this) {
                is State.NoSolutions -> copy(
                    problem = problem.copy(description = intent.description),
                )

                is State.WithSolutions.NoDecision -> copy(
                    problem = problem.copy(description = intent.description),
                )

                is State.WithSolutions.WithDecision -> copy(
                    problem = problem.copy(description = intent.description),
                )
            }
        }

        Intent.AddNewSolution -> {
            when (this) {
                is State.NoSolutions -> State.WithSolutions.NoDecision(
                    problem = problem,
                    solutions = listOf(Solution.empty()),
                )

                is State.WithSolutions.NoDecision -> copy(
                    solutions = solutions + Solution.empty(),
                )

                is State.WithSolutions.WithDecision -> copy(
                    solutions = solutions + Solution.empty(),
                )
            }
        }

        is Intent.ChangeSolutionDescription -> {
            when (this) {
                is State.WithSolutions -> {
                    val updatedSolutions = changeSolutionDescription(
                        solutions = solutions,
                        solutionToChange = intent.solution,
                        descriptionNew = intent.description
                    )
                    when (this) {
                        is State.WithSolutions.NoDecision -> copy(solutions = updatedSolutions)
                        is State.WithSolutions.WithDecision -> copy(solutions = updatedSolutions)
                    }
                }

                else -> error("Illegal state $this for $intent")
            }
        }

        is Intent.SelectSolution -> {
            when (this) {
                is State.WithSolutions -> {
                    val updatedSolutions = changeSolutionsSelection(
                        solutions = solutions,
                        solutionToChange = intent.solution,
                    )
                    when (this) {
                        is State.WithSolutions.NoDecision -> copy(solutions = updatedSolutions)
                        is State.WithSolutions.WithDecision -> copy(solutions = updatedSolutions)
                    }
                }

                else -> error("Illegal state $this for $intent")
            }
        }

        Intent.MakeDecision -> {
            when (this) {
                is State.WithSolutions -> {
                    val selectedSolutions = solutions.filter { it.isSelected }
                    when {
                        selectedSolutions.isEmpty() -> error("No selected solution to make decision")
                        selectedSolutions.size == 1 -> {
                            val selectedSolution = selectedSolutions.first()
                            State.WithSolutions.WithDecision(
                                problem = problem,
                                solutions = solutions,
                                decisionMessage = "Problem: ${problem.description} was solved by: ${selectedSolution.description}"
                            )
                        }

                        selectedSolutions.size > 1 -> error("You must choose only one solution: ${selectedSolutions}")
                        else -> error("Unknown case")
                    }
                }

                else -> error("Illegal state $this for $intent")
            }
        }

        Intent.Clear -> State.NoSolutions(
            problem = Problem.empty(),
            isMakeDecisionEnabled = false,
        )
    }

    private fun changeSolutionDescription(
        solutions: List<Solution>,
        solutionToChange: Solution,
        descriptionNew: String,
    ): List<Solution> {
        return solutions.map { solution ->
            if (solution === solutionToChange) {
                solution.copy(description = descriptionNew)
            } else {
                solution
            }
        }
    }

    private fun changeSolutionsSelection(
        solutions: List<Solution>,
        solutionToChange: Solution,
    ): List<Solution> {
        return solutions.map { solution ->
            if (solution === solutionToChange) {
                solution.copy(isSelected = true)
            } else {
                solution.copy(isSelected = false)
            }
        }
    }
}
