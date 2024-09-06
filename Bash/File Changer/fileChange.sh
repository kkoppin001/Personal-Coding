#!/bin/bash

printHelp() {
    echo "Usage: namechange -f find -r replace \"string to modify\""
    echo " -f The text to find in the filename"
    echo " -r The replacement text for the new filename"
}

findPattern=""

replacePattern=""

filename=""

while getopts ":hf:r:" opt; do

    case $opt in
    
        h)
            printHelp
            ;;
            
        f)
            findPattern="$OPTARG"
            ;;
            
        r)
            replacePattern="$OPTARG"
            ;;
            
        \?)
            printHelp
            exit 1
            ;;
    esac
    
done

if [ $# -eq 0 ] || [ ! -f "$1" ]; then
    echo "User must provide valid filename"
    printHelp
fi


if [[ -z $find_pattern ]] || [[ -z $replace_pattern ]]; then
    echo "Both -f and -r options are required."
    printHelp
fi

filename="$1"
new_filename=$(echo "$filename" | sed -E "s/$findPattern/$replacePattern/")
echo "File renamed from $filename to $new_filename"
