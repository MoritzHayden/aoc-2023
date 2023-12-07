/**
 * Advent of Code 2023, Day 7
 * Link: http://adventofcode.com/2023/day/7
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.CamelCardHand
import dev.hmoritz.aoc2023.models.CamelCardHandType
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
        return parseInput()
            .sortedWith(
                compareByDescending<CamelCardHand> { it.type }
                    .thenBy { it.cardValues[0] }
                    .thenBy { it.cardValues[1] }
                    .thenBy { it.cardValues[2] }
                    .thenBy { it.cardValues[3] }
                    .thenBy { it.cardValues[4] }
            )
            .mapIndexed { index, hand -> hand.bid * (index + 1L) }
            .sum()
            .toString()
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }

    private fun parseInput(): List<CamelCardHand> {
        return input.map { createCamelCardHand(it) }
    }

    private fun createCamelCardHand(line: String): CamelCardHand {
        val splitLine = line.split(" ")
        val rawHand = splitLine[0]
        val bid = splitLine[1].toInt()
        val cardValues = mutableListOf<Int>()
        val cardOccurrences = mutableMapOf<Char, Int>()
        for (card in rawHand) {
            cardValues.add(getCardValue(card))
            cardOccurrences.putIfAbsent(card, 0)
            cardOccurrences[card] = cardOccurrences[card]!! + 1
        }
        val handType = getHandType(cardOccurrences)
        return CamelCardHand(rawHand, bid, handType, cardValues, cardOccurrences)
    }

    private fun getCardValue(card: Char): Int {
        return when (card) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> 11
            'T' -> 10
            else -> card.digitToInt()
        }
    }

    private fun getHandType(cardOccurrences: Map<Char, Int>): CamelCardHandType {
        val maxOccurrences = cardOccurrences.values.maxOrNull() ?: 0
        val pairCount = cardOccurrences.values.count { it == 2 }

        return when {
            maxOccurrences == 5 -> CamelCardHandType.FIVE_OF_A_KIND
            maxOccurrences == 4 -> CamelCardHandType.FOUR_OF_A_KIND
            maxOccurrences == 3 && pairCount == 1 -> CamelCardHandType.FULL_HOUSE
            maxOccurrences == 3 -> CamelCardHandType.THREE_OF_A_KIND
            pairCount == 2 -> CamelCardHandType.TWO_PAIR
            pairCount == 1 -> CamelCardHandType.ONE_PAIR
            else -> CamelCardHandType.HIGH_CARD
        }
    }
}
