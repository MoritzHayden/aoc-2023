/**
 * Advent of Code 2023, Day 6
 * Link: http://adventofcode.com/2023/day/6
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day06() : Day {
    private val filename = filenames[5]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val races = getRacesPart1()
        val raceWaysToWin = mutableListOf<Int>()
        races.forEach { race ->
            var waysToWin = 0
            for (buttonHoldTime in 0..race.first) {
                val remainingTime = race.first - buttonHoldTime
                val distance = remainingTime * buttonHoldTime
                if (distance > race.second) waysToWin++
            }
            raceWaysToWin.add(waysToWin)
        }
        return raceWaysToWin.reduce(Int::times).toString()
    }

    private fun solvePart2(): String {
        val race = getRacePart2()
        var waysToWin = 0
        for (buttonHoldTime in 0..race.first) {
            val remainingTime = race.first - buttonHoldTime
            val distance = remainingTime * buttonHoldTime
            if (distance > race.second) waysToWin++
        }
        return waysToWin.toString()
    }

    // Returns a list of pairs in the form (time, distance)
    private fun getRacesPart1(): List<Pair<Int, Int>> {
        val times = input[0].replace("Time:", "").trim().split("\\s+".toRegex()).map { it.toInt() }
        val distances = input[1].replace("Distance:", "").trim().split("\\s+".toRegex()).map { it.toInt() }
        return times.zip(distances)
    }

    // Returns a pair in the form (time, distance)
    private fun getRacePart2(): Pair<Long, Long> {
        val time = input[0].replace("Time:", "").trim().replace("\\s+".toRegex(), "").toLong()
        val distance = input[1].replace("Distance:", "").trim().replace("\\s+".toRegex(), "").toLong()
        return Pair(time, distance)
    }
}
