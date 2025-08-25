package model

enum class Direction {
    N,E,S,W;

    fun moveForward(pos: RobotState) {
        when (pos.direction) {
            Direction.N -> pos.state.y += 1
            Direction.E -> pos.state.x += 1
            Direction.S -> pos.state.y -= 1
            Direction.W -> pos.state.x -= 1
        }
    }

    fun moveBackward(pos: RobotState) {
        when (pos.direction) {
            Direction.N -> pos.state.y -= 1
            Direction.E -> pos.state.x -= 1
            Direction.S -> pos.state.y += 1
            Direction.W -> pos.state.x += 1
        }
    }

    fun turnLeft(): Direction = when (this) {
        N -> W
        W -> S
        S -> E
        E -> N
    }

    fun turnRight(): Direction = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }
}