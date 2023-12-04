/**
 * Advent of Code 2023, Day 4
 * Link: http://adventofcode.com/2023/day/4
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day04() : Day {
    private val filename = filenames[3]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val cardScores = mutableListOf<Int>()
        input.forEach { line ->
            val splitLine = line.split("|")
            val winningNumbers = splitLine[0]
                .split(":")[1]
                .trim()
                .split("\\s+".toRegex())
                .map { it.toInt() }
                .toSet()
            val myNumbers = splitLine[1]
                .trim()
                .split("\\s+".toRegex())
                .map { it.toInt() }
                .toSet()
            val matchingNumbers = myNumbers.intersect(winningNumbers)
            var cardScore = 0
            matchingNumbers.forEach { _ -> if (cardScore == 0) cardScore++ else cardScore *= 2 }
            cardScores.add(cardScore)
        }
        return cardScores.sum().toString()
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }
}
