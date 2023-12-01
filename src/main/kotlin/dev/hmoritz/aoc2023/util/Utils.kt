package dev.hmoritz.aoc2023.util

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
            "nine" -> 9
            "eight" -> 8
            "seven" -> 7
            "six" -> 6
            "five" -> 5
            "four" -> 4
            "three" -> 3
            "two" -> 2
            "one" -> 1
            "zero" -> 0
            else -> throw IllegalArgumentException("Invalid word: $word")
        }
    }

    // Convert the given string to a resource URI
    private fun String.toURI(): URI =
        Utils.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find resource: $this")
}
