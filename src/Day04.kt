fun main() {
    fun part1(input: List<String>): Int {
        val norm = input.countXmas()
        val trans = input.transpose().countXmas()
        val diamond = input.diamond().countXmas()
        val revDiamond = input.map { it.reversed() }.diamond().countXmas()
        return norm + trans + diamond + revDiamond
    }

    fun part2(input: List<String>): Int {
        return input.countMasX()
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("..X...", ".SAMX.", ".A..A.", "XMAS.S", ".X....")).println() == 4)
    check(part2(listOf("M.S", ".A.", "M.S")).println() == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput).println() == 18)
    check(part2(testInput).println() == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun List<String>.countXmas(): Int {
    return sumOf { "XMAS".toRegex().findAll(it).count() + "SAMX".toRegex().findAll(it).count() }
}

fun List<String>.transpose(): List<String> {
    val result = mutableListOf<String>()
    forEachIndexed { row, rowString ->
        rowString.forEachIndexed { col, char ->
            if (result.size <= col) result.add(col, "".padEnd(row, ' ') + char)
            else result[col] = result[col].padEnd(row, ' ') + char
        }
    }
    return result
}

fun List<String>.diamond(): List<String> {
    val result = mutableListOf<String>()
    for (i in 0 until (size + this[0].length - 1)) {
        result.add("")
    }
    forEachIndexed { row, rowString ->
        rowString.forEachIndexed { col, char ->
            result[row + col] = result[row + col].plus(char)
        }
    }
    return result
}

fun List<String>.countMasX(): Int {
    var count = 0;
    forEachIndexed { row, rowString ->
        rowString.forEachIndexed { col, char ->
            if (char == 'S' || char == 'M') {
                if (checkX(this, row, col)) count = count.inc()
            }
        }
    }
    return count
}

fun checkX(input: List<String>, row: Int, col: Int): Boolean {
    var curRow = row
    var curCol = col
    var main: String = input[curRow][curCol].toString()

    repeat(2) {
        curRow = curRow.inc()
        curCol = curCol.inc()
        if (curRow >= input.size || curCol >= input[0].length) {
            return false
        }
        main = main.plus(input[curRow][curCol])
    }
    curRow = row
    curCol = col + 2
    if (curCol >= input[0].length) {
        return false
    }
    var cross: String = input[curRow][curCol].toString()
    repeat(2) {
        curRow = curRow.inc()
        curCol = curCol.dec()
        if (curRow >= input.size || curCol >= input[0].length) {
            return false
        }
        cross = cross.plus(input[curRow][curCol])
    }
    return (main == "MAS" || main == "SAM") && (cross == "MAS" || cross == "SAM")
}
