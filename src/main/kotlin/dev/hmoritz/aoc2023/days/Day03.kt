/**
 * Advent of Code 2023, Day 3
 * Link: http://adventofcode.com/2023/day/3
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.isSymbol
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day03() : Day {
    private val filename = filenames[2]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val partNumbers = mutableListOf<Int>()
        var currentDigits = ""
        var foundSymbol = false
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    // ********************************************
                    // * topLeft    * topMiddle     * topRight    *
                    // * middleLeft * middle (here) * middleRight *
                    // * bottomLeft * bottomMiddle  * bottomRight *
                    // ********************************************
                    val topLeft = input.getOrNull(i - 1)?.getOrNull(j - 1)
                    val topMiddle = input.getOrNull(i - 1)?.getOrNull(j)
                    val topRight = input.getOrNull(i - 1)?.getOrNull(j + 1)
                    val middleLeft = input[i].getOrNull(j - 1)
                    val middle = input[i][j]
                    val middleRight = input[i].getOrNull(j + 1)
                    val bottomLeft = input.getOrNull(i + 1)?.getOrNull(j - 1)
                    val bottomMiddle = input.getOrNull(i + 1)?.getOrNull(j)
                    val bottomRight = input.getOrNull(i + 1)?.getOrNull(j + 1)

                    currentDigits += middle
                    if (isSymbol(topLeft) || isSymbol(topMiddle) || isSymbol(topRight) ||
                        isSymbol(middleLeft) || isSymbol(middleRight) ||
                        isSymbol(bottomLeft) || isSymbol(bottomMiddle) || isSymbol(bottomRight)
                    ) {
                        foundSymbol = true
                    }
                } else {
                    if (currentDigits.isNotEmpty() && foundSymbol) {
                        partNumbers.add(currentDigits.toInt())
                        foundSymbol = false
                        currentDigits = ""
                    } else if (!foundSymbol) {
                        currentDigits = ""
                    }
                }
            }
        }

        return partNumbers.sum().toString()
    }

    private fun solvePart2(): String {
        val numberIndexes = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        val gearIndexes = mutableListOf<Pair<Int, Int>>()
        val gearRatios = mutableListOf<Int>()
        for (i in input.indices) {
            var numberStartIndex: Pair<Int, Int>? = null
            for (j in input[i].indices) {
                val currentChar = input[i][j]
                if (numberStartIndex == null && currentChar.isDigit()) {
                    numberStartIndex = Pair(i, j)
                } else if (currentChar == '*') {
                    if (numberStartIndex != null) numberIndexes.add(Pair(numberStartIndex, Pair(i, j - 1)))
                    gearIndexes.add(Pair(i, j))
                    numberStartIndex = null
                } else if (numberStartIndex != null && !currentChar.isDigit()) {
                    numberIndexes.add(Pair(numberStartIndex, Pair(i, j - 1)))
                    numberStartIndex = null
                } else if (numberStartIndex != null && j == input[i].lastIndex) {
                    numberIndexes.add(Pair(numberStartIndex, Pair(i, j)))
                    numberStartIndex = null
                }
            }
        }

        gearIndexes.forEach { gearIndex ->
            val foundNumbers = mutableListOf<Int>()
            numberIndexes.forEach { numberIndex ->
                val lowerStartNumberX = numberIndex.first.first - 1
                val lowerStartNumberY = numberIndex.first.second - 1
                val upperEndNumberX = numberIndex.second.first + 1
                val upperEndNumberY = numberIndex.second.second + 1
                if (gearIndex.first in lowerStartNumberX..upperEndNumberX &&
                    gearIndex.second in lowerStartNumberY..upperEndNumberY
                ) {
                    foundNumbers.add(
                        input[numberIndex.first.first].substring(
                            numberIndex.first.second,
                            numberIndex.second.second + 1
                        ).toInt()
                    )
                }
            }
            if (foundNumbers.count() == 2) gearRatios.add(foundNumbers[0] * foundNumbers[1])
        }

        return gearRatios.sum().toString()
    }
}
