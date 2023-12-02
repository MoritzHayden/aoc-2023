package dev.hmoritz.aoc2023.models

data class CubeGame (
    var id: Int,
    var sets: MutableList<CubeGameSet>
)

data class CubeGameSet (
    var redCount: Int,
    var greenCount: Int,
    var blueCount: Int
)
