package aoc2025.day9

import aoc2025.day9.factories.createMap
import aoc2025.day9.factories.getRectangles
import aoc2025.day9.models.Coordinate
import aoc2025.day9.models.Rectangle
import kotlin.system.measureNanoTime

fun main() {

    var redTiles = setOf<Coordinate>()
    val inputTime = measureNanoTime {
        redTiles = {}::class.java
            .getResource("/day9/input.txt")
            ?.readText()
            ?.lines()
            ?.map { it.split(",").map { n -> n.toInt() } }
            ?.map { Coordinate(it[0], it[1]) }
            ?.toSet()
            ?: throw IllegalStateException("Could not load")
    }
    println("Made coordinate set in ${inputTime / 1_000_000.0}ms.")

    var rectangles = listOf<Rectangle>()
    val part1Time = measureNanoTime {
        rectangles = getRectangles(redTiles)
    }
    println("Part 1: ${rectangles.first().size} in ${part1Time / 1_000_000.0}ms.")

    var size = 0L
    val part2Time = measureNanoTime {
        val map = createMap(redTiles)
        val maxX = map.keys.maxOf { it.x } + 1
        val maxY = map.keys.maxOf { it.y } + 1
        val validator = RectangleValidator(map, maxX, maxY)
        size = rectangles.firstOrNull { validator.isValid(it) }?.size ?: 0L
    }
    println("Part 2: $size in ${part2Time / 1_000_000.0}ms.")
}

