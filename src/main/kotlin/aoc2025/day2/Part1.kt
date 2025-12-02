package aoc2025.day2

class Part1: Part() {
    override fun hasRepeatedSequence(id: Long): Boolean {
        val length = getLength(id)
        val chunk = id % pow10[length / 2]
        return chunk + chunk * pow10[length / 2] == id
    }
}