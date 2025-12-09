package aoc2025.day9.models

data class Coordinate(val x: Int,
                      val y: Int) {
    fun next(direction: Direction): Coordinate {
        return Coordinate(x + direction.dx, y + direction.dy)
    }
    fun inBounds(maxX: Int, maxY: Int): Boolean {
        return (x in 0..maxX && y in 0..maxY)
    }
}