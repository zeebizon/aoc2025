package aoc2025.day2

import kotlin.system.measureTimeMillis

fun main() {
    var input: List<Pair<Long, Long>>
    val inputTime = measureTimeMillis {
        input = {}::class.java
            .getResource("/day2/input.txt")
            ?.readText()
            ?.split(",")
            ?.map { e -> e.split("-") }
            ?.map { (a, b) -> a.toLong() to b.toLong() }
            ?: error("Input not found or invalid")
    }
    println("Read input in $inputTime ms.")

    var part1 = 0L
    val part1Time = measureTimeMillis {
        part1 = Part1().password(input)
    }
    println("Part 1, with just equal bits: $part1. Took $part1Time ms.")

    var part2 = 0L
    val part2Time = measureTimeMillis {
        part2 = Part2().password(input)
    }
    println("Part 2, any repeated sequence: $part2. Took $part2Time ms.")
}