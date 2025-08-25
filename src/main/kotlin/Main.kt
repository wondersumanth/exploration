import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import model.Direction
import model.ExplorationInput
import model.ExplorationWithInstruction
import model.RobotDestination
import model.RobotInput
import model.RobotState
import model.TravelInstruction
import model.TwoDimensionalPoint
import service.Navigation
import service.impl.EuropaNavigation

fun main(args: Array<String>) {
    val mapper = jacksonObjectMapper()
   // var json = java.io.File(args[0]).readText()

        """
        {
          "gridSize": {
            "x": 5,
            "y": 5
          },
          "robotInputs": [
            {
              "robotState": {
                "state": {
                  "x": 1,
                  "y": 2
                },
                "direction": "N"
              },
              "travelInstruction": "LMLMLMLMM"
            },
            {
              "robotState": {
                "state": {
                  "x": 3,
                  "y": 3
                },
                "direction": "E"
              },
              "travelInstruction": "MMRMMRMRRM"
            },
            {
              "robotState": {
                "state": {
                  "x": 6,
                  "y": 6
                },
                "direction": "E"
              },
              "travelInstruction": "MMRMMRMRRM"
            }
          ]
        }
    """.trimIndent()
    /*val explorationWithInstruction: ExplorationWithInstruction = mapper.readValue(json,
        ExplorationWithInstruction::class.java)*/
    val explorationWithInstruction: ExplorationWithInstruction = ExplorationWithInstruction(
        explorationInputs = listOf(
            ExplorationInput(
                gridSize = TwoDimensionalPoint(5,5),
                robotInputs = listOf(RobotInput(
                    RobotState(TwoDimensionalPoint(1,2), direction = Direction.N), initialFuel = 100, robotId = 1
                    )),
                planetId = 1
            ),
            ExplorationInput(
                gridSize = TwoDimensionalPoint(3,3),
                robotInputs = listOf(RobotInput(
                    RobotState(TwoDimensionalPoint(0,0), direction = Direction.E), initialFuel = 100, robotId = 2
                )),
                planetId = 2
            )
        ),
        instruction = listOf(
            TravelInstruction(planetId = 2, robotId = 2, instruction = "MMLMMRMRRM"),
            TravelInstruction(planetId = 1, robotId = 1, instruction = "LMLMLMLMM"),
        )
    )

    val results: MutableList<RobotDestination> = mutableListOf()
    explorationWithInstruction.instruction.forEach {instruction ->
        val navigation: Navigation = EuropaNavigation()
        val explorationInput=  explorationWithInstruction.explorationInputs.find { it.planetId==instruction.planetId }!!
        val result: List<RobotDestination> = navigation.getFinalState(explorationInput,instruction)
        results.addAll(result)
    }

    println(results)
}