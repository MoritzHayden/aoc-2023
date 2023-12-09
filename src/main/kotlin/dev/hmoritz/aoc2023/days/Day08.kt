/**
 * Advent of Code 2023, Day 8
 * Link: http://adventofcode.com/2023/day/8
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.BinaryTreeNode
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile

class Day08() : Day {
    private val filename = filenames[7]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        // TODO: This works on the test input, but hangs on the real input.
        //  May need different data structure or remove cyclical references.
        val instructions = parseInstructions()
        val root = parseTree()
        var currentNodeValue = "AAA"
        var steps = 0
        println()
        while (currentNodeValue != "ZZZ") {
            steps++
            val instruction = instructions[(steps - 1) % instructions.size]
            print("Step $steps: Moving $instruction from $currentNodeValue to ")
            when (instruction) {
                'L' -> currentNodeValue = root.find(currentNodeValue)?.getLeft()?.getValue() ?: currentNodeValue
                'R' -> currentNodeValue = root.find(currentNodeValue)?.getRight()?.getValue() ?: currentNodeValue
            }
            println(currentNodeValue)
        }
        return steps.toString()
    }

    private fun solvePart2(): String {
        // STUB
        return ""
    }

    private fun parseInstructions(): CharArray {
        return input[0].toCharArray()
    }

    private fun parseTree(): BinaryTreeNode<String> {
        val tree = BinaryTreeNode("AAA")
        for (i in 2..<input.size) {
            val splitLine = input[i].split(" = ")
            val root = splitLine[0]
            val children = splitLine[1]
                .replace("(", "")
                .replace(")", "")
                .replace(",", "")
                .split(" ")
            val left = children[0]
            val right = children[1]
            if (root != left) tree.find(root)?.addLeft(left)
            if (root != right) tree.find(root)?.addRight(right)
        }
        return tree
    }
}
