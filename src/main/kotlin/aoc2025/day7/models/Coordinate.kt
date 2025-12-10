package aoc2025.day7.models

data class Position (val x: Int, val y: Int) {
    fun down() = Position(x, y+1)
    fun left() = Position(x-1, y)
    fun right() = Position(x+1, y)

    fun canMoveDown(mapSize: Int) = y+1 < mapSize
}
