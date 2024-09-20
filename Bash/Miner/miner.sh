#!/bin/bash

# Define file paths
DICTIONARY="experiment.txt"
OUTPUT_FILE="trial.txt"
EXAMPLES=("4Beasley's" "anotherExample") # Add all example nonce/word combos to this array

# Function to check if a combination is an example
is_example() {
    local combo="$1"
    for example in "${EXAMPLES[@]}"; do
        if [[ "$combo" == "$example" ]]; then
            return 0
        fi
    done
    return 1
}

# Trap Ctrl+C (SIGINT) to exit cleanly
trap "echo 'Exiting...'; exit" SIGINT

# Counter for 3-point coins
three_point_coin_count=0
target_coins=3

# Main loop to generate KFC Coins
while [ "$three_point_coin_count" -lt "$target_coins" ]; do
    # Generate a random nonce (2 to 10 digits)
    nonce=$(shuf -i 10-9999999999 -n 1)

    # Select a random word from the dictionary, trimming whitespace
    word=$(shuf -n 1 "$DICTIONARY" | xargs)

    # Create the combination
    combo="$nonce$word"

    # Hash the combination
    hash=$(printf "%s" "$combo" | sha256sum | awk '{print $1}')

    # Count leading zeros in the hash
    points=$(echo -n "$hash" | sed -E 's/^(0*).*/\1/' | wc -c)

    # Skip if it's an example nonce/word combo
    if is_example "$combo"; then
        continue
    fi

    # Print the points message
    echo "Generated a $points-point coin: $combo"

    # If it's a 4-point coin, quit immediately
    if [ "$points" -eq 4 ]; then
        echo "Found a 4-point coin: $combo. Exiting..."
        exit 0
    fi

    # Only save 3-point coins
    if [ "$points" -eq 3 ]; then
        echo "$combo" >> "$OUTPUT_FILE"
        echo "Added a 3-point coin: $combo"
        
        # Increment the 3-point coin counter
        three_point_coin_count=$((three_point_coin_count + 1))
    fi
done

echo "Found $target_coins 3-point coins. Exiting..."

