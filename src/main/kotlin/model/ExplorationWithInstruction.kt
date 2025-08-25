package model

data class ExplorationWithInstruction(
    val explorationInputs: List<ExplorationInput>,
    val instruction: List<TravelInstruction>
)
