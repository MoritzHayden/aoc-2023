package dev.hmoritz.aoc2023.models

data class AlmanacMap(
    val type: AlmanacMapType,
    val almanacMapEntries: List<AlmanacMapEntry>
)

data class AlmanacMapEntry(
    val source: Long,
    val destination: Long,
    val range: Long
)

enum class AlmanacMapType {
    SEED_TO_SOIL,
    SOIL_TO_FERTILIZER,
    FERTILIZER_TO_WATER,
    WATER_TO_LIGHT,
    LIGHT_TO_TEMPERATURE,
    TEMPERATURE_TO_HUMIDITY,
    HUMIDITY_TO_LOCATION
}
