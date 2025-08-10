package model

data class ExplorationInput(
    val gridSize: TwoDimensionalPoint,
    val robotInputs: List<RobotInput>
)
