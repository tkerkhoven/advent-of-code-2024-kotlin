fun main() {
    fun part1(input: List<String>): Int {
        val pattern = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        val matches = pattern.findAll(input.joinToString(""))
        return matches.sumOf { it.destructured.component1().toInt() * it.destructured.component2().toInt() }
    }

    fun part2(input: List<String>): Int {
        val instructions = input.joinToString("").replace("\n", "")
        val split = instructions.split("do()")
        val enabled = split.map { it.split("don't()")[0] }
        return part1(enabled)
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("mul(44,46)mul(4*")) == 2024)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161*3)
    part2(testInput).println()
    check(part2(testInput) == 48*3)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
