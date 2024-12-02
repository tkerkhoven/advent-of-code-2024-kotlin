import kotlin.collections.zipWithNext
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val levelsList = parseInput(input)
        return levelsList.count { it.hasSafeLevels() }
    }

    fun part2(input: List<String>): Int {
        val levelsList = parseInput(input)

        return levelsList.count { hasSafeDampenedLevels(it) }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("1 1", "2 1", "1 2")) == 2)
    part2(listOf("1 1", "2 1", "1 2", "1 1 1", "1 2 1 2 1", "1 1 5", "1 1 4")).println()
//    check(part2(listOf("1 1", "2 1", "1 2", "1 1 1", "1 2 1 2 1", "1 1 5", "1 1 4")) == 4)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    part2(testInput).println()
//    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun parseInput(input: List<String>): List<List<Int>> {
    return input.map { it.split(" ") }.map { it.map { it.toInt() } }
}

fun hasSafeDampenedLevels(levels: List<Int>): Boolean {
    var permutations: MutableList<List<Int>> = mutableListOf(levels)
    levels.forEachIndexed { index, _ -> permutations.add(levels.filterIndexed { i, _ -> i != index }) }
    return permutations.any { it.hasSafeLevels() }
}

fun List<Int>.hasSafeLevels(): Boolean {
    val pairs = zipWithNext()
    return (pairs.all { it.isIncreasing() } || pairs.all { it.isDecreasing() }) && pairs.all { it.hasSafeDistance() }
}

fun Pair<Int, Int>.isIncreasing(): Boolean = first < second
fun Pair<Int, Int>.isDecreasing(): Boolean = first > second

fun Pair<Int, Int>.hasSafeDistance(): Boolean {
    val distance = abs(first - second)
    return distance <= 3 && distance != 0
}
