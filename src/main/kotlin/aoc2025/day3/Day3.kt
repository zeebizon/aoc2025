package aoc2025.day3

import kotlin.math.pow
import kotlin.system.measureNanoTime

fun main() {
    val part = 2
    var input: List<IntArray>
    val inputTime = measureNanoTime {
        input = {}::class.java
            .getResource("/day3/input.txt")
            ?.readText()
            ?.lines()
            ?.map { str -> str.map {
                it.digitToInt()
            }.toIntArray() }
            ?: error("Input not found or invalid")
    }

    var result = 0L
    val partTime = measureNanoTime {
        val joltage = Joltage()
        result = input.sumOf { joltage.highestJoltage(it, if (part == 2) 12 else 2) }
    }

    println("Read input in ${inputTime / 1_000_000.0} ms.")
    println("Part $part: found $result in ${partTime / 1_000_000.0} ms.")
}

class Joltage {
    val powers = LongArray(20)

    init {
        powers[0] = 1
        for (i in 1 until 20) {
            powers[i] = powers[i - 1] * 10
        }
    }

    fun highestJoltage(batteryBank: IntArray, amount: Int): Long {
        val batteries = LongArray(amount)
        var index = 0
        for (i in 0 until amount) {
            val end = batteryBank.size - (amount - 1 - i)
            val window = batteryBank.slice(index until end)

            var highest = 0
            var highestIndex = 0
            for (i in 0 until window.size) {
                if (window[i] > highest) {
                    highest = window[i]
                    highestIndex = i
                    if (highest == 9)
                        break;
                }
            }
            index += highestIndex

            batteries[i] = highest * powers[amount - 1 - i]
            index++ // move past previously chosen index, to move the window along
        }
        return batteries.sumOf { it }
    }
}
