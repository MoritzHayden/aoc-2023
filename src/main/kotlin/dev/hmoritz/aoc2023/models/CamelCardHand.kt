package dev.hmoritz.aoc2023.models

data class CamelCardHand(
    val raw: String,
    val bid: Int,
    val type: CamelCardHandType,
    val cardValues: List<Int>,
    val cardOccurrences: Map<Char, Int>,
)

enum class CamelCardHandType(val rank: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1),
    UNKNOWN(0)
}
