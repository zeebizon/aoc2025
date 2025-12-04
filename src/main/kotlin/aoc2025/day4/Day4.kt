package aoc2025.day4

import kotlin.collections.indices
import kotlin.system.measureNanoTime

fun main() {
    var input: Array<CharArray>
    val inputTime = measureNanoTime {
        input = {}::class.java
            .getResource("/day4/input.txt")
            ?.readText()
            ?.lines()
            ?.map { str -> str.toCharArray() }
            ?.toTypedArray()
            ?: error("Input not found or invalid")
    }
    println("Read input in ${inputTime / 1_000_000.0} ms.")

    var totalRemoved = 0
    val processTime = measureNanoTime {

        val paperRolls = PaperRolls()
        var removed = 1

        while (removed > 0) {
            val (accessible, newMap) = paperRolls.removeAccessibleRolls(input)
            input = newMap
            removed = accessible
            totalRemoved += removed
        }

    }

    println("Removed $totalRemoved in ${processTime / 1_000_000.0} ms.")

}

class PaperRolls {

    val neighbors = listOf(
        -1 to -1, -1 to 0, -1 to 1,
         0 to -1,           0 to 1,
         1 to -1,  1 to 0,  1 to 1
    )

    fun removeAccessibleRolls(input: Array<CharArray>): Pair<Int, Array<CharArray>> {
        val height = input.size
        val width = input[0].size
        val adjacentMap = calculateAdjacentRolls(height, width, input)

        var accessible = 0
        val newMap = Array(height) { CharArray(width) { '.' } }
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '.') continue
                if (adjacentMap[i][j] >= 4)
                    newMap[i][j] = '@'
                else if (input[i][j] == '@')
                    accessible++
            }
        }
        return accessible to newMap
    }

    private fun calculateAdjacentRolls(
        height: Int,
        width: Int,
        input: Array<CharArray>
    ): Array<IntArray> {
        val adjacentMap = Array(height) { IntArray(width) { 0 } }

        for (i in input.indices) {
            for (j in input[i].indices) {
                val field = input[i][j]
                if (field == '@') {
                    incrementNeighbours(i, j, adjacentMap)
                }
            }
        }
        return adjacentMap
    }

    fun incrementNeighbours(i: Int, j: Int, adjacentMap: Array<IntArray>) {
        for ((hi, wi) in neighbors) {
            val ni = i + hi
            val nj = j + wi

            if ( ni in adjacentMap.indices && nj in adjacentMap[ni].indices )
                adjacentMap[ni][nj]++
        }
    }
}