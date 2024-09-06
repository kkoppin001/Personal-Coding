#!/bin/bash

# Input and output file paths
input_file="quiz_data.csv"
output_file="salted_data.csv"
salts_file="salts.txt"

# Create an associative array to store salts for unique identifiers
declare -A salts

# Generate a unique salt
generate_salt() {
  echo "$(tr -dc 'a-zA-Z0-9' </dev/urandom | head -c 5)"
}

# Clear the salts file before starting
> "$salts_file"

# Read the input CSV file line by line
{
  read -r header # Read and store the header
  echo "$header" > "$output_file" # Write the header to the output file

  while IFS=, read -r first_column rest_of_line; do
    if [[ -z "${salts[$first_column]}" ]]; then
      # Generate a new salt for unique identifiers
      salt=$(generate_salt)
      salts[$first_column]=$salt
      
      # Write the unique salt to the salts file
      echo "$salt" >> "$salts_file"
    fi

    salt="${salts[$first_column]}"
    salted_identifier="${salt}${first_column}"

    # Generate SHA-256 hash
    hashed_identifier=$(printf "%s" "$salted_identifier" | sha256sum | awk '{print $1}')

    # Prepend the salt to the hash
    final_identifier="${salt}${hashed_identifier}"

    # Write the new line to the output file with the salted and hashed identifier
    echo "$final_identifier,$rest_of_line" >> "$output_file"

  done
} < "$input_file"

echo "Salted and hashed data saved to $output_file"
echo "Unique salts saved to $salts_file"

