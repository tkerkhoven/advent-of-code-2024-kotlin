import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val levelss = getInput(input)
        return levelss.count { isInOrDecreasing(it) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("1 1","2 1","1 2")) == 2)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun getInput(input: List<String>): List<List<Int>> {
    return input
        .map { it.split(" ") }
        .map { it.map { it.toInt() } }
}

fun isInOrDecreasing(nums: List<Int>): Boolean {
    val pairs = nums.zipWithNext()
    return (pairs.all { (a, b) -> a < b } || pairs.all { (a, b) -> a > b }) && pairs.all { (a, b) -> abs(a - b) <= 3 }
}
