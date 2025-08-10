package service.impl

import model.ExplorationInput
import model.RobotDestination
import model.RobotState
import model.TwoDimensionalPoint
import service.Navigation

class EuropaNavigation: Navigation {
    override fun getFinalState(explorationInput: ExplorationInput): List<RobotDestination> {

        val gridMaxX = explorationInput.gridSize.x
        val gridMaxY = explorationInput.gridSize.y
        val result = mutableListOf<RobotDestination>()
        explorationInput.robotInputs.forEach { robotInput ->
            val travelInstruction = robotInput.travelInstruction.toCharArray()
            val initialState: TwoDimensionalPoint = robotInput.robotState.state
            var initialDirection = robotInput.robotState.direction
            val errors = mutableListOf<String>()
            for (direction in travelInstruction) {
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
                   else ->{
                       errors.add( "Invalid instruction")
                       break
                   }
               }
            }
            if(errors.isNotEmpty()){
                result.add(RobotDestination(
                    endState = null,
                    errorAnalysis = errors
                ))
            }else{
                result.add(RobotDestination(
                    direction = initialDirection,
                    endState = initialState
                ))
            }

        }
        return result
    }
}

