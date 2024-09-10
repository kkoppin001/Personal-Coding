
# Word Game Project  

## Project Description  

This program involves one turn in a simple word game based on one from the New York Times
called *Spelling Bee.* In this game, a user tries to make as many valid words as possible from a list of letters 
to get the highest score that they can. This project has use of ArrayLists, methods for various functions, and the use
of input/output work with files.

## Project Guide

### Dependencies  

What is needed to run this code, you need a supported Integrated Development Environment(*IDE*), a text file
titled "*words.txt*" that lists many words to choose from and it needs to be in the same directory as the coding file, and the JDK Extension as this is a Java-based program

### How to run the project  

If a *Run button* is not available, like it usually is in **Visual Studio Code** in the top right corner, 
you can type two commands into the terminal, also known as the command line.

The two commands that you will want to use, and the order DOES matter, first, "**javac wordsGame.java**" to compile the program, and then "**java wordsGame.java**" second 
to run it. Then the program should run accordingly.

### How to play the game  

To play the game, a list of 6 letters will appear on the screen in the terminal. To gain points, you have to use the
letters given and then try and make words using those letters, and the letters listed can be used more than once. 4 letters words are worth 1 point, 
and then after that, however many letters you use greater than 4, you get that many points. ie; 5 letters = 5 points, 6 letters = 6 points, and so on.

There are also certain commands that you can use as functions to help you with playing the game.

**ls**: can be used to list all of the words that you have typed out already so you do not repeat words. If you have not typed any words, the program will say "*No words 
entered. Please enter words.*"

**mix**: can be used to shuffle the letters already given if you are struggling to find words

**bye**: will terminate the program and then the game will end

### Notes To Know

**If a word was already used:** If the user types in a word that has already been used, the program will warn the user by saying "*Word is already used, please try again*" 
and then prompt the user for a valid word or listed command.

**If a typed word does not contain 4 or more letters:** If the user types in a word that in less than four letters, the program will warn the user by saying "*Word must be 
at least 4 letters, please try again*" and then prompt the user for a valid word or listed command.

**If a typed word contains letters that were not listed in the output:** If the user types in a word that contains letters that were not listed in the output, the program 
will warn the user by saying "*Word is using letters not listed in the given ones, please try again.*" and will then prompt the user for a valid word or listed command.

**If a word is not listed in the *words.txt* file:**  If the user types in a word that is not listed in the file, the program will warn the user by saying "*Could not find 
word, please try again*" and then prompt the user for a valid word or listed command.

## Lessons Learned 

Some of the lessons and bugs that I faced while making this project were with the use of while loops. Sometimes the while loops would become infinite loops and it was a 
struggle to try and where to put the correct break statement.

The next problem was what to pass into the methods for the program and learning what should or should not be passed into the methods. I learned to think about the code and 
why I am passing things in and why it works the way it does.

The last lesson That I learned was the use of ArrayLists especially with the use of lists made up of Strings and made up of characters specifically. It was difficult 
sometimes to differentiate between the two, but it was a good learning curve of why lists made up of characters would be better for some things while some made up 
of strings would be better for others.
