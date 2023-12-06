/**
 * Advent of Code 2023, Day 5
 * Link: http://adventofcode.com/2023/day/5
 */
package dev.hmoritz.aoc2023.days

import dev.hmoritz.aoc2023.models.AlmanacMap
import dev.hmoritz.aoc2023.models.AlmanacMapEntry
import dev.hmoritz.aoc2023.models.AlmanacMapType
import dev.hmoritz.aoc2023.models.Day
import dev.hmoritz.aoc2023.util.Constants.filenames
import dev.hmoritz.aoc2023.util.Utils.readFile
import dev.hmoritz.aoc2023.util.Utils.writeSolutionsToFile
import kotlin.math.min

class Day05() : Day {
    private val filename = filenames[4]
    private val input = readFile(filename)

    override fun solve() {
        val solutionPart1 = solvePart1()
        val solutionPart2 = solvePart2()
        writeSolutionsToFile(filename, solutionPart1, solutionPart2)
    }

    private fun solvePart1(): String {
        val seeds = getSeedsPart1()
        val almanacMaps = getAlmanacMaps()
        val soilDestinations = mutableListOf<Long>()
        val fertilizerDestinations = mutableListOf<Long>()
        val waterDestinations = mutableListOf<Long>()
        val lightDestinations = mutableListOf<Long>()
        val temperatureDestinations = mutableListOf<Long>()
        val humidityDestinations = mutableListOf<Long>()
        val locationDestinations = mutableListOf<Long>()

        mapDestinations(seeds, soilDestinations, almanacMaps, AlmanacMapType.SEED_TO_SOIL)
        mapDestinations(soilDestinations, fertilizerDestinations, almanacMaps, AlmanacMapType.SOIL_TO_FERTILIZER)
        mapDestinations(fertilizerDestinations, waterDestinations, almanacMaps, AlmanacMapType.FERTILIZER_TO_WATER)
        mapDestinations(waterDestinations, lightDestinations, almanacMaps, AlmanacMapType.WATER_TO_LIGHT)
        mapDestinations(lightDestinations, temperatureDestinations, almanacMaps, AlmanacMapType.LIGHT_TO_TEMPERATURE)
        mapDestinations(temperatureDestinations, humidityDestinations, almanacMaps, AlmanacMapType.TEMPERATURE_TO_HUMIDITY)
        mapDestinations(humidityDestinations, locationDestinations, almanacMaps, AlmanacMapType.HUMIDITY_TO_LOCATION)

        return locationDestinations.min().toString()
    }

    private fun solvePart2(): String {
        // TODO: Optimize this (threading?)
        val seedRanges = getSeedsPart2()
        val almanacMaps = getAlmanacMaps()
        var lowestLocation = Long.MAX_VALUE
        for (seedRange in seedRanges) {
            for (seed in seedRange.first..seedRange.first + seedRange.second) {
                lowestLocation = min(lowestLocation, resolveSeedLocation(seed, almanacMaps))
            }
        }
        return lowestLocation.toString()
    }

    private fun getSeedsPart1(): List<Long> {
        return input[0].replace("seeds: ", "").split(" ").map { it.toLong() }
    }

    private fun getSeedsPart2(): List<Pair<Long, Long>> {
        val seedsTemp = input[0].replace("seeds: ", "").split(" ").map { it.toLong() }
        val seedRanges = mutableListOf<Pair<Long, Long>>()
        for (i in 1 until seedsTemp.size step 2) {
            seedRanges.add(Pair(seedsTemp[i - 1], seedsTemp[i]))
        }
        return seedRanges
    }

    private fun resolveSeedLocation(seed: Long, almanacMaps: List<AlmanacMap>): Long {
        return mapDestination(
            mapDestination(
                mapDestination(
                    mapDestination(
                        mapDestination(
                            mapDestination(
                                mapDestination(seed, almanacMaps, AlmanacMapType.SEED_TO_SOIL),
                                almanacMaps,
                                AlmanacMapType.SOIL_TO_FERTILIZER
                            ), almanacMaps, AlmanacMapType.FERTILIZER_TO_WATER
                        ), almanacMaps, AlmanacMapType.WATER_TO_LIGHT
                    ), almanacMaps, AlmanacMapType.LIGHT_TO_TEMPERATURE
                ), almanacMaps, AlmanacMapType.TEMPERATURE_TO_HUMIDITY
            ), almanacMaps, AlmanacMapType.HUMIDITY_TO_LOCATION
        )
    }

    private fun mapDestination(
        source: Long,
        almanacMaps: List<AlmanacMap>,
        mapType: AlmanacMapType
    ): Long {
        return resolveDestination(source, almanacMaps.first { it.type == mapType })
    }

    private fun mapDestinations(
        sourceList: List<Long>,
        destinationList: MutableList<Long>,
        almanacMaps: List<AlmanacMap>,
        mapType: AlmanacMapType
    ) {
        sourceList.forEach { source ->
            destinationList.add(
                resolveDestination(
                    source,
                    almanacMaps.first { it.type == mapType })
            )
        }
    }

    private fun resolveDestination(value: Long, almanacMap: AlmanacMap): Long {
        almanacMap.almanacMapEntries.forEach { almanacMapEntry ->
            if (value in almanacMapEntry.source..(almanacMapEntry.source + (almanacMapEntry.range - 1))) {
                return almanacMapEntry.destination + (value - almanacMapEntry.source)
            }
        }
        return value
    }

    private fun getAlmanacMaps(): List<AlmanacMap> {
        val almanacMaps = mutableListOf<AlmanacMap>()
        val currentAlmanacMapEntries = mutableListOf<AlmanacMapEntry>()
        var currentMapType: AlmanacMapType? = null
        for (i in input.indices) {
            if ((i == input.lastIndex) || (input[i].isBlank() && currentMapType != null)) {
                almanacMaps.add(
                    AlmanacMap(
                        type = currentMapType!!,
                        almanacMapEntries = currentAlmanacMapEntries.toMutableList()
                    )
                )
                currentAlmanacMapEntries.clear()
                currentMapType = null
            } else if (currentMapType != null) {
                val mapValues = input[i].split(" ").map { it.toLong() }
                currentAlmanacMapEntries.add(
                    AlmanacMapEntry(
                        source = mapValues[1],
                        destination = mapValues[0],
                        range = mapValues[2]
                    )
                )
            } else {
                currentMapType = getCurrentMapType(input[i])
            }
        }
        return almanacMaps
    }

    private fun getCurrentMapType(line: String): AlmanacMapType? {
        return when {
            line.contains("seed-to-soil map") -> AlmanacMapType.SEED_TO_SOIL
            line.contains("soil-to-fertilizer map") -> AlmanacMapType.SOIL_TO_FERTILIZER
            line.contains("fertilizer-to-water map") -> AlmanacMapType.FERTILIZER_TO_WATER
            line.contains("water-to-light map") -> AlmanacMapType.WATER_TO_LIGHT
            line.contains("light-to-temperature map") -> AlmanacMapType.LIGHT_TO_TEMPERATURE
            line.contains("temperature-to-humidity map") -> AlmanacMapType.TEMPERATURE_TO_HUMIDITY
            line.contains("humidity-to-location map") -> AlmanacMapType.HUMIDITY_TO_LOCATION
            else -> null
        }
    }
}
