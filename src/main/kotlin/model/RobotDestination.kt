package model

data class RobotDestination(
    val endState: TwoDimensionalPoint? = null,
    val direction: Direction?=null,
    val errorAnalysis: List<String> = emptyList(),
    val remainingFuel: Int,
    val planetId: Int
)
