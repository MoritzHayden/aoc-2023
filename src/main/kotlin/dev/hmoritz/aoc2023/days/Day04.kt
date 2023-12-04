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
            val winningNumbers = getWinningNumbers(line)
            val myNumbers = getMyNumbers(line)
            val matchingNumbers = myNumbers.intersect(winningNumbers)
            var cardScore = 0
            matchingNumbers.forEach { _ -> if (cardScore == 0) cardScore++ else cardScore *= 2 }
            cardScores.add(cardScore)
        }
        return cardScores.sum().toString()
    }

    private fun solvePart2(): String {
        var totalCards = 0
        val cardCopyCount = mutableMapOf<Int, Int>()
        input.forEach { line ->
            val cardNumber = getCardNumber(line)
            val cardsToCheck = 1 + cardCopyCount.getOrDefault(cardNumber, 0)
            val winningNumbers = getWinningNumbers(line)
            val myNumbers = getMyNumbers(line)
            val matchingNumberCount = myNumbers.intersect(winningNumbers)
            var cardCopyNumber = cardNumber + 1
            matchingNumberCount.forEach { _ ->
                if (cardCopyCount.containsKey(cardCopyNumber)) {
                    cardCopyCount[cardCopyNumber] = cardCopyCount[cardCopyNumber]!! + cardsToCheck
                } else {
                    cardCopyCount[cardCopyNumber] = cardsToCheck
                }
                cardCopyNumber++
            }
            totalCards += cardsToCheck
        }
        return totalCards.toString()
    }

    private fun getCardNumber(line: String): Int {
        return line
            .split("|")[0]
            .split(":")[0]
            .split("\\s+".toRegex())[1]
            .toInt()
    }

    private fun getWinningNumbers(line: String): Set<Int> {
        return line
            .split("|")[0]
            .split(":")[1]
            .trim()
            .split("\\s+".toRegex())
            .map { it.toInt() }
            .toSet()
    }

    private fun getMyNumbers(line: String): Set<Int> {
        return line
            .split("|")[1]
            .trim()
            .split("\\s+".toRegex())
            .map { it.toInt() }
            .toSet()
    }
}
