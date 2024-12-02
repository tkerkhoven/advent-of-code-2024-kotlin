import kotlin.collections.unzip
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (a, b) = toLists(input)
        val c = a.sorted().zip(b.sorted())
        return c.sumOf { abs(it.first - it.second) }
    }

    fun part2(input: List<String>): Int {
        val (a, b) = toLists(input)
        var c = 0;
        for (num in a) {
            c += num * b.count { it == num }
        }
        return c
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("1   2")) == 1)
    check(part2(listOf("1   1")) == 1)
    check(part2(listOf("1   0")) == 0)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun toLists(input: List<String>): Pair<List<Int>, List<Int>> {
    val pattern = Regex("(\\d+)\\s+(\\d+)")
    val (a, b) = input
        .map { pattern.find(it)!!.destructured }
        .map { it.component1().toInt() to it.component2().toInt() }
        .unzip()
    return Pair(a, b)
}
