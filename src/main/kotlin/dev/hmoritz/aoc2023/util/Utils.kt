package dev.hmoritz.aoc2023.util

import dev.hmoritz.aoc2023.util.Constants.symbols
import java.io.File
import java.net.URI

object Utils {
    // Read the given file and return its lines as a list of strings
    fun readFile(filename: String) = File("input/$filename".toURI()).readLines()

    // Write the given string to the given file
    fun writeFile(filename: String, content: String) =
        File("src/main/kotlin/dev/hmoritz/aoc2023/solutions/$filename").writeText(content)

    // Write the given solutions to the given file
    fun writeSolutionsToFile(filename: String, solutionPart1: String, solutionPart2: String) {
        val solutions = buildString {
            this.append("Part 1:\n")
            this.append("$solutionPart1\n")
            this.append("\nPart 2:\n")
            this.append("$solutionPart2\n")
        }

        writeFile(filename, solutions)
    }

    // Convert the given word to a digit
    fun wordToDigit(word: String): Int {
        if (word.toDoubleOrNull() != null) return word.toInt()

        return when (word) {
            "zero" -> 0
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            else -> throw IllegalArgumentException("Invalid word: $word")
        }
    }

    fun isSymbol(char: Char) = char in symbols

    // Convert the given string to a resource URI
    private fun String.toURI(): URI =
        Utils.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find resource: $this")
}
