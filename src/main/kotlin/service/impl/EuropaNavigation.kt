package service.impl

import model.ExplorationInput
import model.RobotDestination
import model.RobotState
import model.TravelInstruction
import model.TwoDimensionalPoint
import service.Navigation

class EuropaNavigation: Navigation {
    override fun getFinalState(explorationInput: ExplorationInput,travelInstructionForRobot: TravelInstruction): List<RobotDestination> {

        val gridMaxX = explorationInput.gridSize.x
        val gridMaxY = explorationInput.gridSize.y
        val result = mutableListOf<RobotDestination>()
        val travelInstruction = travelInstructionForRobot.instruction.toCharArray()
        val robotId = travelInstructionForRobot.robotId
        val robot = explorationInput.robotInputs.find { it.robotId==robotId }
        explorationInput.robotInputs.forEach { robotInput ->

            val initialState: TwoDimensionalPoint = robotInput.robotState.state
            var initialDirection = robotInput.robotState.direction
            var remainingFuel: Int = robotInput.initialFuel
            val errors = mutableListOf<String>()
            for (direction in travelInstruction) {
                if(remainingFuel<=0){
                    errors.add("No fuel is available")
                    break
                }
               when(direction){
                   'L' -> initialDirection = initialDirection.turnLeft()
                   'R' -> initialDirection = initialDirection.turnRight()
                   'M' -> {
                       initialDirection.moveForward(RobotState(initialState,initialDirection))
                       if(initialState.x !in 0..gridMaxX || initialState.y !in 0..gridMaxY){
                           errors.add( "Coordinates are out of the grid")
                           break
                       }
                   }
                   'B' -> {
                       initialDirection.moveBackward(RobotState(initialState,initialDirection))
                       if(initialState.x !in 0..gridMaxX || initialState.y !in 0..gridMaxY){
                           errors.add( "Coordinates are out of the grid")
                           break
                       }
                   }
                   else ->{
                       errors.add( "Invalid instruction")
                       break
                   }
               }
                remainingFuel--
            }
            if(errors.isNotEmpty()){
                result.add(RobotDestination(
                    endState = initialState,
                    direction = initialDirection,
                    errorAnalysis = errors,
                    remainingFuel = remainingFuel,
                    planetId = explorationInput.planetId
                ))
            }else{
                result.add(RobotDestination(
                    direction = initialDirection,
                    endState = initialState,
                    remainingFuel = remainingFuel,
                    planetId = explorationInput.planetId
                ))
            }

        }
        return result
    }
}

