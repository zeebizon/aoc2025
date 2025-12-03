package aoc2025.day3

import kotlin.math.pow
import kotlin.system.measureTimeMillis

fun main() {
    var input: List<IntArray>
    val inputTime = measureTimeMillis {
        input = {}::class.java
            .getResource("/day3/input.txt")
            ?.readText()
            ?.lines()
            ?.map { str -> str.map {
                it.digitToInt()
            }.toIntArray() }
            ?: error("Input not found or invalid")
    }
    val part1Time = measureTimeMillis {
        println("Part 1: ${input.sumOf { highestJoltage(it, 2) }}")
    }
    val part2Time = measureTimeMillis {
        println("Part 2: ${input.sumOf { highestJoltage(it, 12) }}")
    }
    println("Read input in $inputTime ms.")
    println("Part 1 in $part1Time ms.")
    println("Part 2 in $part2Time ms.")
}

fun highestJoltage(batteryBank: IntArray, amount: Int) : Long {
    val batteries = LongArray(amount);
    var index = -1
    for (i in 1..amount) {
        index += 1
        val max = batteryBank.slice(index until batteryBank.size - (amount-i)).maxOfOrNull { it } ?: 0
        index += batteryBank.slice(index until batteryBank.size - (amount-i)).indexOfFirst { it == max }
        batteries[i-1] = max * 10.0.pow(amount-i).toLong()
    }
    return batteries.sumOf { it }
}