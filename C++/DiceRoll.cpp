/*
Author: Kalli Koppin
Class: CS 3100 Data Structures & Algorithms
Due Date: September 13th, 2024
Summary: This program simulates a dice rolling game. The user will enter the amount of times
they want to roll two standard six sided dice. The program will generate the random amounts
and then print out how many times a certain number was rolled.

Resources used:
srand() and rand(): https://www.geeksforgeeks.org/rand-and-srand-in-ccpp/
*/

#include <iostream> // used for standard 
#include <cstdlib> // used to import the random function
#include <ctime> // used for the time function

using namespace std; // used for simplicity

int main() {

    /*====================================================================
    VARIABLE DECLARATIONS
    ====================================================================*/
    int numRolls;
    int dice1;
    int dice2;
    int rollTotals;
    int const maxTotal = 12; // maximum amount that can be rolled is 12
    int const minTotal = 2; // minimum amount that can be rolled in 2
    int arr[maxTotal + 1] = {0}; // constant size array
    /*==================================================================*/

    // used to seed the random number generator
    srand(time(nullptr));

    /*====================================================================
    begin getting user input 
    ====================================================================*/
    cout << "How many rolls? ";
    cin >> numRolls;
    cout << "Simulating " << numRolls << " rolls...";


    /*====================================================================
    for i starting at 0 and i will be less than the number of rolls the 
    user chose, the dice will obtain values and update the array for the
    frequency of what was rolled
    ====================================================================*/
    for (int i = 0; i < numRolls; i++){
        dice1 = rand() % 6 + 1;
        dice2 = rand() % 6 + 1;
        rollTotals = dice1 + dice2;
        arr[rollTotals] ++;

    }

    /*====================================================================
    begin printing the results of the dice rolls 
    ====================================================================*/
    cout << "\nResults:\n";
    
    /*====================================================================
    for total starting at the minimum total, if the total is less than
    the maximum total (2-12), increase total by 1 after each iteration.
    This will then print the result from the array and show how many times
    each amount was rolled.
    ====================================================================*/
    for (int total = minTotal; total <= maxTotal; total++){
        cout << total << " was rolled  " << arr[total] << " times\n";
    }

    return 0; // end program
}


