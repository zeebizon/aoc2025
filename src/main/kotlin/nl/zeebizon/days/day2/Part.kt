package nl.zeebizon.days.day2

abstract class Part {

    val pow10: LongArray = LongArray(20) { 1L }.also { arr ->
        for (i in 1 until arr.size) arr[i] = arr[i - 1] * 10
    }

    fun password(input: List<Pair<Long, Long>>): Long {
        var invalid = 0L
        for (range in input) {
            val start = maxOf(10, range.first)
            for (i in start ..range.second) {
                invalid += if (hasRepeatedSequence(i)) i else 0L
            }
        }
        return invalid
    }

    fun getLength(number: Long): Int {
        var length = 1
        var copy = number
        while (copy >= 10) {
            length++
            copy /= 10
        }
        return length
    }

    abstract fun hasRepeatedSequence(id: Long): Boolean
}