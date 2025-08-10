import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import model.ExplorationInput
import model.RobotDestination
import service.Navigation
import service.impl.EuropaNavigation

fun main(args: Array<String>) {
    val mapper = jacksonObjectMapper()
    var json = java.io.File(args[0]).readText()

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
    val explorationInput: ExplorationInput = mapper.readValue(json, ExplorationInput::class.java)
    val navigation: Navigation = EuropaNavigation()
    val result: List<RobotDestination> = navigation.getFinalState(explorationInput)
    println(result)
}