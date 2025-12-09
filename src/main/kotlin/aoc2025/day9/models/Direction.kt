package aoc2025.day9.models

enum class Direction(val dx: Int, val dy: Int) {
    N(0, -1),
    W(-1, 0),
    S(0, 1),
    E(1, 0);
}