#!/bin/bash

#####################################################################################################
# Description: This script will download the input for each day of the Advent of Code, if it exists #
# Prerequisite: Set your AoC session token as an environment variable named AOC_SESSION_TOKEN       #
# Usage: ./scripts/refresh-input.sh                                                                 #
# Author: Hayden Moritz                                                                             #
#####################################################################################################

YEAR=2023
echo "Refreshing input for Advent of Code $YEAR..."

for DAY in {1..25}
do
    DAY_FORMATTED=$(printf "%02d" "$DAY")
    URL="https://adventofcode.com/$YEAR/day/$DAY/input"
    OUTPUT_FILE="src/main/resources/input/day$DAY_FORMATTED.txt"
    RESPONSE=$(curl -s -w "%{http_code}" --cookie "session=$AOC_SESSION_TOKEN" $URL)
    RESPONSE_HTTP_CODE=$(tail -n1 <<< "$RESPONSE")
    RESPONSE_CONTENT=$(sed '$ d' <<< "$RESPONSE")

    if [ "$RESPONSE_HTTP_CODE" == "200" ]; then
        echo "$RESPONSE_CONTENT" > "$OUTPUT_FILE"
        echo "Day $DAY input saved to $OUTPUT_FILE (HTTP $RESPONSE_HTTP_CODE)"
    else
        echo "Day $DAY input not found (HTTP $RESPONSE_HTTP_CODE)"
    fi
done

echo "Refreshed input for Advent of Code $YEAR!"
