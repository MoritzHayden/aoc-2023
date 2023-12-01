/**
 * Advent of Code 2023, Day 1
 * Link: http://adventofcode.com/2023/day/1
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.wordToDigit
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day01() : Day {
    private val filename = filenames[0]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        var sum = 0
        input.forEach { line ->
            val lineDigits = line.filter { it.isDigit() }
            sum += "${lineDigits.first()}${lineDigits.last()}".toInt()
        }
        return sum.toString()
    }

    private fun solvePart2(): String {
        var sum = 0
        input.forEach { line ->
            val regex = Regex("(one|two|three|four|five|six|seven|eight|nine|\\d)")
            val matches = regex.findAll(line).toList()
            sum += "${wordToDigit(matches.first().value)}${wordToDigit(matches.last().value)}".toInt()
        }
        return sum.toString()
    }
}
