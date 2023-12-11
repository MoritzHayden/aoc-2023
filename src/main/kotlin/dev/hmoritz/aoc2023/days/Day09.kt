/**
 * Advent of Code 2023, Day 9
 * Link: http://adventofcode.com/2023/day/9
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile
import kotlin.math.abs

class Day09() : Day {
    private val filename = filenames[8]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        // TODO: Debug why this works for the test input but not the real input
        //  1908969839 is too high
        return input.sumOf { line -> calculateNextNumber(line.split("\\s+".toRegex()).map { it.toLong() }) }.toString()
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }

    private fun calculateNextNumber(numbers: List<Long>): Long {
        if (numbers.all { it == 0L }) return 0L
        val pattern = mutableListOf<Long>()
        for (i in 0..<numbers.indices.last) {
            pattern.add(abs(numbers[i] - numbers[i + 1]))
        }
        return numbers.last() + calculateNextNumber(pattern)
    }
}
