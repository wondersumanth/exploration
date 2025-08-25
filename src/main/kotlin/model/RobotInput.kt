package model

data class RobotInput(
    val robotState: RobotState,
    val initialFuel: Int,
    val robotId: Int
)
