package aoc2025.day9

import aoc2025.day9.models.Coordinate
import aoc2025.day9.models.Direction
import aoc2025.day9.models.Rectangle

class RectangleValidator(
    private val map: Map<Coordinate, Char>,
    private val maxX: Int,
    private val maxY: Int
) {

    private val lineCache = hashMapOf<Pair<Coordinate, Coordinate>, Boolean>()

    fun isValid(rect: Rectangle): Boolean {
        return allCornersInMap(rect)
                && !anyLineCrosses(rect)
    }

    private fun allCornersInMap(rect: Rectangle): Boolean {
        return listOf(
            rect.nw to listOf(Direction.N, Direction.W),
            rect.ne to listOf(Direction.N, Direction.E),
            rect.se to listOf(Direction.S, Direction.E),
            rect.sw to listOf(Direction.S, Direction.W)
        ).all { (corner, dirs) ->
            dirs.all { coordIsIn(corner, it) }
        }
    }

    private fun coordIsIn(coord: Coordinate, direction: Direction): Boolean {
        var pos = coord
        while (pos.inBounds(maxX, maxY)) {
            if (pos in map) return true
            pos = pos.next(direction)
        }
        return false
    }

    private fun anyLineCrosses(rect: Rectangle): Boolean {
        return listOf(
            rect.nw to rect.sw,
            rect.nw to rect.ne,
            rect.se to rect.ne,
            rect.se to rect.sw
        ).any { (start, end) -> lineCache.getOrPut(start to end) { crossesLine(start, end) } }
    }

    private fun crossesLine(start: Coordinate, end: Coordinate): Boolean {
        require(start.x == end.x || start.y == end.y)
        var beforeLine = false
        var onLine = false
        val range = if (start.x == end.x) exclusiveRange(start.y, end.y).map { Coordinate(start.x, it) }
        else exclusiveRange(start.x, end.x).map { Coordinate(it, start.y) }
        for (pos in range) {
            when {
                !beforeLine && pos !in map -> beforeLine = true
                beforeLine && pos in map -> onLine = true
                onLine && pos !in map -> return true
            }
        }
        return false
    }
}
