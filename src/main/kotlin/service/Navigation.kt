package service

import model.ExplorationInput
import model.RobotDestination

interface Navigation {

    fun getFinalState(explorationInput: ExplorationInput): List<RobotDestination>
}