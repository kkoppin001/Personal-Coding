
# Word Game Project  

## Project Description  

This program is a domino-like tile game, where the objective is to determine the maximum score possible by arranging the tiles according to the scoring rules of the game.

## Project Guide

### Dependencies  

What is needed to run this code, you need a supported Integrated Development Environment(*IDE*) and the JDK Extension as this is a Java-based program

### How to run the project  

If a *Run button* is not available, like it usually is in **Visual Studio Code** in the top right corner, 
you can type two commands into the terminal, also known as the command line.

The two commands that you will want to use, and the order DOES matter, first, "**javac TileGame.java**" 
to compile the program, and then "**java TileGame.java**" second 
to run it. Then the program should run accordingly.

### How to play the game  

To play the game, the user will be prompted to enter domino tiles in the manner of `2 | 1 , 1 | 2` or 
other valid numbers. The code will then score the input and your goal is to get the highest score you possibly 
can. If you type in and the two numbers on the inside are matching, then the score will be doubled. If they are not matching, the program will choose the highest scoring tile. The catch of this game is making it match the cirteria listed in the **Notes to Know** section.


**Notes To Know**

- **If the user types too long of an input:**
    - The program will respond to too long of an input and say `Output: Invalid input. quitting…`

- **If the user types too shory of an input:**
    - The program will respond to too short of an input and say `Output: Invalid input. quitting…`

- **If the user does not use the "|" (pipe) symbol in the correct spots:**
    - The program will respond with `Output: Invalid input, does not use the \"|\" symbol, quitting…`

- **If the user does not use the "," (comma) symbol in the correct spots:**
    - The program will respond with `Output: Invalid input, does not use the \",\" symbol, quitting…`

- **If the user enters in a number less than 1 (one) or greater than 3 (three):**
    - The program will respond with `Output: Invalid input. quitting…1



## Lessons Learned 

One of the biggest lessons I learned while developing this project is making sure the indexes are correct. If you wanted the sixth spot in the input, and you put "check index 6" instead of 5, youre code will not run correctly because indexes start at 0. 

The second lesson learned is checking all possible factors with your if-statements. If youre missing one if statement, then the program can be ran completely differently.

