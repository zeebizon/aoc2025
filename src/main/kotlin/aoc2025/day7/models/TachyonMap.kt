package aoc2025.day7.models

class TachyonMap (val map: List<List<Char>>) {
    val start: Position = Position(map[0].indexOf('S'), 0)
    val size = map.size
    val visited = hashMapOf<Position, Long>()

    fun findWorlds(): Long {
        visited.clear()
        return findSplitters(start) { visited.getOrDefault(it, 0L) } + 1
    }
    fun findSplitters(): Long {
        visited.clear()
        return findSplitters(start) { 0L }
    }

    fun findSplitters(position: Position, cache: (Position) -> Long): Long {
        // for findSplitters this is just 0L, for findWorlds it is how many splitters below this point
        if (position in visited)
            return cache(position)

        var splitters = 0L
        if (position.canMoveDown(size)) { // Is terminal?
            var next = position.down()
            if (charAt(next) == '^') {
                splitters += 1L
                splitters += findSplitters(next.left(), cache)
                splitters += findSplitters(next.right(), cache)
            } else {
                splitters += findSplitters(next, cache)
            }
        }

        visited.put(position, splitters)
        return splitters
    }

    fun charAt(p: Position) = map[p.y][p.x]
}