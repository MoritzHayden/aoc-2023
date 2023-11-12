/**
 * Advent of Code 2023, Day 7
 * Link: http://adventofcode.com/2023/day/7
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day07() : Day {
    private val filename = filenames[6]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        // STUB
        return ""
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }
}
