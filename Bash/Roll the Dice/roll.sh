#!/bin/bash

# Color codes
RED='\033[0;31m'   # Red color
BLUE='\033[0;34m'  # Blue color
GREEN='\033[0;32m' # Green color
NC='\033[0m'       # No color (reset)

askForDiceAmount() {
    while true; do
        # Ask user for dice amount
        echo "How many dice would you like to roll? "
        read amount

        # Check if the input is a number and greater than 0
        if ! [[ "$amount" =~ ^[1-9][0-9]*$ ]]; then
            echo -e "${RED}ERROR: Please enter a valid number greater than 0.${NC}"
            continue
        fi

        # Confirmation
        echo "Are you sure you want to roll $amount dice? (yes or no) "
        read confirmation

        # If user says yes, continue; if no, ask amount again
        if [[ "$confirmation" == "yes" || "$confirmation" == "y" ]]; then
            echo "You chose to roll $amount dice."
            # Call askForSides function to proceed
            askForSides
            break  # Exit the loop after confirming and proceeding
        else
            echo -e "${RED}ERROR: Let's try again...${NC}"
        fi
    done
}

askForSides() {
    while true; do
        echo "How many sides would you like your dice to have? "
        read sides

        # Check if the input is a number and greater than 0
        if ! [[ "$sides" =~ ^[1-9][0-9]*$ ]]; then
            echo -e "${RED}ERROR: Please enter a valid number greater than 0.${NC}"
            continue
        fi

        echo "Are you sure you want to roll a $sides-sided die? (yes or no) "
        read confirmation

        if [[ "$confirmation" == "yes" || "$confirmation" == "y" ]]; then
            echo "You chose to roll a $sides-sided dice."
            rollDice "$amount" "$sides"  # Call the rollDice function
            break  # Exit the loop after confirming
        else
            echo -e "${RED}ERROR: Let's try again...${NC}"
        fi
    done
}

rollDice() {
    local dice_count=$1
    local sides=$2
    echo "Rolling $dice_count dice with $sides sides..."
    
    total=0  # Initialize total sum

    # Roll the dice and display results
    for ((i = 0; i < dice_count; i++)); do
        roll=$((RANDOM % sides + 1))  # Generate a random number between 1 and sides
        echo -e "${BLUE}You rolled a $roll${NC}"  # Display the result in blue
        total=$((total + roll))  # Add roll to total
    done

    # Display the total sum in green
    echo -e "${GREEN}Total sum of rolled dice: $total${NC}"
}

# Call the function to start the script
askForDiceAmount

