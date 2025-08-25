package service

import model.ExplorationInput
import model.RobotDestination
import model.TravelInstruction

interface Navigation {

    fun getFinalState(explorationInput: ExplorationInput, instruction: TravelInstruction): List<RobotDestination>
}