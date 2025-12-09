package aoc2025.day9

fun exclusiveRange(a: Int, b: Int): IntProgression =
    if (a < b) (a + 1 until b) else (a - 1 downTo b + 1)