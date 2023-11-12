package dev.hmoritz.aoc2023

import dev.hmoritz.aoc2023.util.Constants.days

fun main() {
    println("Starting AoC 2023 Solutions!")
    println("****************************")

    for ((index, day) in days.withIndex()) {
        print("Solving Day ${index + 1}...")
        day.solve()
        println(" Solved!")
    }

    println("***************************")
    println("Exiting AoC 2023 Solutions!")
}
