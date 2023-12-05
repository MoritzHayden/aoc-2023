#!/bin/bash

########################################################################################################
# Author: Hayden Moritz                                                                                #
# Description: This script will download the input for each day of the Advent of Code, if it exists    #
# Prerequisites:                                                                                       #
#   1. Set your AoC session token as an environment variable named AOC_SESSION_TOKEN. For example:     #
#      export AOC_SESSION_TOKEN="53616c823..."                                                         #
#   2. Set your AoC user agent string as an environment variable named $AOC_USER_AGENT. For example:   #
#      export AOC_USER_AGENT="github.com/testuser/aoc-solutions by test@testuser.com"                  #
# Usage: ./scripts/refresh-input.sh                                                                    #
########################################################################################################

YEAR=2023
echo "Refreshing input for Advent of Code $YEAR..."

for DAY in {1..25}
do
    DAY_FORMATTED=$(printf "%02d" "$DAY")
    URL="https://adventofcode.com/$YEAR/day/$DAY/input"
    OUTPUT_DIR="${BASH_SOURCE%/*}/../src/main/resources/input"
    OUTPUT_FILE="$OUTPUT_DIR/day$DAY_FORMATTED.txt"
    RESPONSE=$(curl -s -w "%{http_code}" -A "$AOC_USER_AGENT" -b "session=$AOC_SESSION_TOKEN" $URL)
    RESPONSE_HTTP_CODE=$(tail -n1 <<< "$RESPONSE")
    RESPONSE_CONTENT=$(sed '$ d' <<< "$RESPONSE")

    mkdir -p "$OUTPUT_DIR"
    touch "$OUTPUT_FILE"

    if [ "$RESPONSE_HTTP_CODE" == "200" ]; then
        echo "$RESPONSE_CONTENT" > "$OUTPUT_FILE"
        echo "Day $DAY input saved to $OUTPUT_FILE (HTTP $RESPONSE_HTTP_CODE)"
    else
        echo "Day $DAY input not found (HTTP $RESPONSE_HTTP_CODE)"
    fi
done

echo "Refreshed input for Advent of Code $YEAR!"
