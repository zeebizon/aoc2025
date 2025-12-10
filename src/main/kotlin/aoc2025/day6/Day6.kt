package aoc2025.day6

import java.util.regex.Pattern
import kotlin.math.min

fun main() {
    val input = {}::class.java
        .getResource("/day6/input.txt")
        ?.readText()
        ?.lines()
        ?: throw IllegalStateException("Could not load")

    val spaces = input.last()
        .split(Pattern.compile("[+*]"))
        .map { it.length }
        .drop(1)
        .dropLast(1)
        .toMutableList()
        .apply { add(20) }

    val calculations = MutableList(spaces.size) { mutableListOf<CharArray>() }
    for (line in input) {
        var rest = line
        for ((i, s) in spaces.withIndex()) {
            calculations[i].add(rest.substring(0, min(rest.length, s)).toCharArray())
            rest = rest.substring(min(rest.length, s + 1)) // remove what we took, + the extra space
        }
    }

    println("Part 1: ${doCalculations(calculations)}")
    println("Part 2: ${fromRightToLeft(calculations)}")
}

fun doCalculations(input: List<List<CharArray>>): Long {
    var total = 0L
    for (calc in input) {
        val parts = calc.map { it.concatToString().trim() }
        var operand = parts.last()
        var subTotal = parts.first().toLong()
        for (n in parts.drop(1).dropLast(1)) {
            when (operand) {
                "*" -> subTotal *= n.toLong()
                "+" -> subTotal += n.toLong()
            }
        }
        total += subTotal
    }
    return total
}

fun fromRightToLeft(input: List<List<CharArray>>): Long {
    val flipped = mutableListOf<List<CharArray>>()
    for (calc in input) {
        val width = calc.maxOfOrNull { it.size } ?: 0
        val rows = MutableList(width) { mutableListOf<Char>() }
        for (x in width - 1 downTo 0)
            for (y in 0 until calc.size - 1)
                if (x in calc[y].indices)
                    rows[width - 1 - x].add(calc[y][x])
        flipped.add(rows.map { it.toCharArray() } + calc.last())
    }
    return doCalculations(flipped)
}