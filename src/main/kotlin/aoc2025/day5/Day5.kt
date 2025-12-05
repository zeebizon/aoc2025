package aoc2025.day5

import kotlin.system.measureNanoTime

fun main() {
    var processedInput: ProcessInputResult
    val inputTime = measureNanoTime {
        val input = {}::class.java
            .getResource("/day5/input.txt")
            ?.readText()
            ?: throw IllegalStateException("Input not found")
        processedInput = processInput(input)
    }
    var (idRanges, ids) = processedInput

    var totalIds = 0
    val part1Time = measureNanoTime {
        id@ for (id in ids)
            for (range in idRanges)
                if (id >= range[0] && id <= range[1]) {
                    totalIds += 1
                    continue@id
                }
    }

    var totalPossibleIds = 0L

    val part2Time = measureNanoTime {
        var changing = true
        while (changing) {
            changing = false
            val reducedRanges = mutableListOf<MutableList<Long>>()
            range@ for (range in idRanges) {
                for (otherRange in reducedRanges) {
                    if (range === otherRange) // dont merge with self
                        continue
                    if (range[0] <= otherRange[1] && range[1] >= otherRange[0]) {
                        changing = true
                        otherRange[0] = minOf(range[0], otherRange[0])
                        otherRange[1] = maxOf(range[1], otherRange[1])
                        continue@range
                    }
                }
                reducedRanges.add(mutableListOf(range[0], range[1]))
            }
            idRanges = reducedRanges
        }
    }

    for (range in idRanges)
        totalPossibleIds += range[1] - range[0] + 1


    println("Processed input in ${inputTime / 1_000_000.0}ms.")
    println("Part 1: $totalIds, in ${part1Time / 1_000_000.0}ms.")
    println("Part 2: $totalPossibleIds, in ${part2Time / 1_000_000.0}ms.")


}


data class ProcessInputResult(val validRanges: List<MutableList<Long>>,
                              val ids: List<Long>)

fun processInput(text: String): ProcessInputResult{
    val input = text
        .split("\n\n")
        .map { it.trim() }

    val validIds = input[0].lines()
        .map { s -> s.split("-").map { inner -> inner.toLong() }.toMutableList() }

    val ids = input[1].lines()
        .map { it.toLong() }
        .toList()
    return ProcessInputResult(validIds, ids)
}