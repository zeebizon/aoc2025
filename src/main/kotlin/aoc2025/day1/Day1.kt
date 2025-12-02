package aoc2025.day1

import kotlin.system.measureTimeMillis

fun main() {

    val time = measureTimeMillis {
        var input = {}::class.java
            .getResource("/day1/input.txt")
            ?.readText()
            ?.lines()
            ?.map { l ->
                if (l[0] == 'R') Integer.valueOf(l.substring(1))
                else -1 * Integer.valueOf(l.substring(1)) }
            ?: error("Input not found or invalid")

        var pos = 50
        var count1 = 0
        var count2 = 0
        for (i in input) {
            val range = if (i > 0) 1..i else  -1 downTo i;
            for (j in range) {
                pos += if (j > 0) 1 else -1
                if (pos == 100)
                    pos = 0
                if (pos == -1)
                    pos = 99

                if (pos == 0)
                    count2++
            }
            if (pos == 0)
                count1++
        }

        println("Part 1: $count1")
        println("Part 2: $count2")

    }
    println("In $time ms.")
}