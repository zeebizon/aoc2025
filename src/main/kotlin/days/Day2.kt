package nl.zeebizon.days

import kotlin.math.ceil
import kotlin.system.measureTimeMillis

fun main() {
    val input = {}::class.java
        .getResource("/day2/real.txt")
        ?.readText()
        ?.split(",")
        ?.map { e -> e.split("-") }
        ?.map { (a, b) -> a.toLong() to b.toLong() }
        ?: error("Input not found or invalid")

    var part1 = 0L
    val part1Time = measureTimeMillis {
        part1 = part1(input)
    }
    println("Part 1, with just equal bits: $part1. Took $part1Time ms.")

    var part2 = 0L
    val part2Time = measureTimeMillis {
        part2 = part2(input)
    }
    println("Part 2, any repeated sequence: $part2. Took $part2Time ms.")
}

fun part1(input: List<Pair<Long, Long>>): Long {
    var invalid = 0L
    for (range in input) {
        for (i in maxOf(10, range.first)..range.second) {
            if (i < 10) continue
            val str = i.toString()
            if (str.chunked(ceil(str.length / 2.0).toInt()).distinct().size == 1)
                invalid += i;

        }
    }
    return invalid;
}

fun part2(input: List<Pair<Long, Long>>): Long {
    var invalid = 0L
    for (range in input) {
        each_id@ for (i in maxOf(10, range.first) ..range.second) {
            val str = i.toString()
            for (j in 1..<str.length) {
                if (str.length % j == 0) {
                    if (str.chunked(j).distinct().size != 1) continue;
                    invalid += i;
                    continue@each_id;
                }
            }
        }
    }
    return invalid;
}