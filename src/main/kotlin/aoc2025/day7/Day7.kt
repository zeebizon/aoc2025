package aoc2025.day7

import aoc2025.day7.models.TachyonMap


fun main() {
    var input = {}::class.java.getResource("/day7/input.txt")
        ?.readText()
        ?.lines()
        ?.map(String::toCharArray)
        ?.map(CharArray::toMutableList)
        ?: throw IllegalStateException("Couldn't load the input")

    val map = TachyonMap(input)
    println("Part 1: ${map.findSplitters()}")
    println("Part 2: ${map.findWorlds()}")
}

