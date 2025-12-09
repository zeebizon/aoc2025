package aoc2025.day9.factories

import aoc2025.day9.exclusiveRange
import aoc2025.day9.models.Coordinate

fun createMap(redTiles: Set<Coordinate>): Map<Coordinate, Char> {
    val map = hashMapOf<Coordinate, Char>()
    for (tile in redTiles) {
        map[tile] = '#'
        for (otherTile in redTiles) {
            if (tile == otherTile) continue
            if (tile.x == otherTile.x) {
                for (y in exclusiveRange(tile.y, otherTile.y)) {
                    val c = Coordinate(tile.x, y)
                    if (map[c] != '#')
                        map[c] = 'X'
                }
            } else if (tile.y == otherTile.y) {
                for (x in exclusiveRange(tile.x, otherTile.x)) {
                    val c = Coordinate(x, tile.y)
                    if (map[c] != '#')
                        map[c] = 'X'
                }
            }
        }
    }
    return map
}