/**
 * Advent of Code 2023, Day 2
 * Link: http://adventofcode.com/2023/day/2
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.CubeGame
import dev.hmoritz.aoc2023.models.CubeGameSet
import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day02() : Day {
    private val filename = filenames[1]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val cubeGames = parseCubeGames()
        val maxRedCubes = 12
        val maxGreenCubes = 13
        val maxBlueCubes = 14
        val possibleCubeGameIds = mutableListOf<Int>()

        cubeGames.forEach { cubeGame ->
            var possibleGame = true
            cubeGame.sets.forEach { cubeGameSet ->
                if (cubeGameSet.redCount > maxRedCubes ||
                    cubeGameSet.greenCount > maxGreenCubes ||
                    cubeGameSet.blueCount > maxBlueCubes) {
                    possibleGame = false
                }
            }
            if (possibleGame) {
                possibleCubeGameIds.add(cubeGame.id)
            }
        }

        return possibleCubeGameIds.sum().toString()
    }

    private fun solvePart2(): String {
        val cubeGames = parseCubeGames()
        val cubeGamePowers = mutableListOf<Int>()

        cubeGames.forEach { cubeGame ->
            var maxRedCubes = 0
            var maxGreenCubes = 0
            var maxBlueCubes = 0
            cubeGame.sets.forEach { cubeGameSet ->
                if (cubeGameSet.redCount > maxRedCubes) maxRedCubes = cubeGameSet.redCount
                if (cubeGameSet.greenCount > maxGreenCubes) maxGreenCubes = cubeGameSet.greenCount
                if (cubeGameSet.blueCount > maxBlueCubes) maxBlueCubes = cubeGameSet.blueCount
            }
            cubeGamePowers.add(maxRedCubes * maxGreenCubes * maxBlueCubes)
        }

        return cubeGamePowers.sum().toString()
    }

    private fun parseCubeGames(): MutableList<CubeGame> {
        val cubeGames = mutableListOf<CubeGame>()
        input.forEach { line ->
            val game = line.split(":")
            val gameId = game[0].filter { it.isDigit() }.toInt()
            val rawGameSets = game[1]
                .replace(" ", "")
                .split(";")
            val parsedGameSets = mutableListOf<CubeGameSet>()
            rawGameSets.forEach { rawGameSet ->
                val cubeCounts = rawGameSet.split(",")
                var redCount = 0
                var greenCount = 0
                var blueCount = 0
                cubeCounts.forEach { cubeCount ->
                    if (cubeCount.contains("red")) {
                        redCount = cubeCount.filter { it.isDigit() }.toInt()
                    } else if (cubeCount.contains("green")) {
                        greenCount = cubeCount.filter { it.isDigit() }.toInt()
                    } else if (cubeCount.contains("blue")) {
                        blueCount = cubeCount.filter { it.isDigit() }.toInt()
                    }
                }
                parsedGameSets.add(CubeGameSet(redCount, greenCount, blueCount))
            }
            cubeGames.add(CubeGame(gameId, parsedGameSets))
        }
        return cubeGames
    }
}
