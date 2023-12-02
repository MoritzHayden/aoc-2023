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
        val calibrationValues = mutableListOf<Int>()
        input.forEach { line ->
            val lineDigits = line.filter { it.isDigit() }
            val calibrationValue = (wordToDigit(lineDigits.first().toString()) * 10) + wordToDigit(lineDigits.last().toString())
            calibrationValues.add(calibrationValue)
        }
        return calibrationValues.sum().toString()
    }

    private fun solvePart2(): String {
        val calibrationValues = mutableListOf<Int>()
        input.forEach { line ->
            val regex = Regex("\\d|one|two|three|four|five|six|seven|eight|nine")
            val matches = regex.findAll(line)
            val calibrationValue = (wordToDigit(matches.first().value) * 10) + wordToDigit(matches.last().value)
            calibrationValues.add(calibrationValue)
        }
        return calibrationValues.sum().toString()
    }
}
