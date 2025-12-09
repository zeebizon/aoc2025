package aoc2025.day9.factories

import aoc2025.day9.models.Coordinate
import aoc2025.day9.models.Rectangle
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun getRectangles(redTiles: Set<Coordinate>): List<Rectangle> {
    val rectangles = mutableListOf<Rectangle>()
    for (tile in redTiles) {
        for (otherTile in redTiles) {
            if (tile == otherTile) continue
            val size = (abs(tile.x - otherTile.x) + 1L) * (abs(tile.y - otherTile.y) + 1L)
            val nw = Coordinate(min(tile.x, otherTile.x), min(tile.y, otherTile.y))
            val ne = Coordinate(max(tile.x, otherTile.x), min(tile.y, otherTile.y))
            val se = Coordinate(max(tile.x, otherTile.x), max(tile.y, otherTile.y))
            val sw = Coordinate(min(tile.x, otherTile.x), max(tile.y, otherTile.y))
            rectangles += Rectangle(size, nw, ne, se, sw)
        }
    }
    return rectangles.sortedByDescending { it.size }
}