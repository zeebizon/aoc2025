package nl.zeebizon.days.day2

class Part2: Part() {
    override fun hasRepeatedSequence(id: Long): Boolean {
        val length = getLength(id)
        for (i in 2 .. length) {
            if (length % i == 0) {
                val constructedId = constructCopy(length, i, id)
                if (constructedId == id) {
                    return true
                }
            }
        }
        return false
    }

    private fun constructCopy(length: Int, i: Int, id: Long): Long {
        val lastNDigits = length / i
        var chunk = id % pow10[lastNDigits]
        var constructedId = chunk
        for (j in 1 until i) {
            chunk *= pow10[lastNDigits]
            constructedId += chunk
        }
        return constructedId
    }
}