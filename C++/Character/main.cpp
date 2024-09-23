/*
Author: Kalli Koppin
Class: CS 3100 Data Structures & Algorithms
Due Date: September 22th, 2024
Summary: This program simulates a simplified character vs character combat game.
The program will ask the user to create 2 characters and choose the stats and 
then will simulate a combat situation for them using random numbers and the
numbers the user input
*/

#include <iostream>
#include "Character.h"
using namespace std;

int main() {

    /*====================================================================
    VARIABLE DECLARATIONS
    ====================================================================*/
    string name1;
    string role1;
    string name2;
    string role2;
    int hp1;
    int ab1;
    int db1;
    int ac1;
    int hp2;
    int ab2;
    int db2;
    int ac2;
    /*==================================================================*/

    /*====================================================================
    begin getting user input for characrer creation #1
    ====================================================================*/
    cout << "First character name? ";
    cin >> name1; // name character 1
    cout << name1 << "'s role? ";
    cin >> role1; // role character 1
    cout << name1 << " the " << role1 << "'s hit points? ";
    cin >> hp1; // hit points character 1
    cout << name1 << " the " << role1 << "'s attack bonus? ";
    cin >> ab1; // atatck bonus character 1
    cout << name1 << " the " << role1 << "'s damage bonus? ";
    cin >> db1; // damage bonus character 1
    cout << name1 << " the " << role1 << "'s armor class? ";
    cin >> ac1; // armour class character 1

    // character 1 created
    Character character1(name1, role1, hp1, ab1, db1, ac1);
    // print summary
    character1.print(cout);


    /*====================================================================
    begin getting user input for characrer creation #2
    ====================================================================*/
    cout << "\nSecond character name? ";
    cin >> name2; // name character 2
    cout << name2 << "'s role? ";
    cin >> role2; // role character 2
    cout << name2 << " the " << role2 << "'s hit points? ";
    cin >> hp2; // hit points character 2
    cout << name2 << " the " << role2 << "'s attack bonus? ";
    cin >> ab2; // attack bonus character 2
    cout << name2 << " the " << role2 << "'s damage bonus? ";
    cin >> db2; // defense bonus character 2
    cout << name2 << " the " << role2 << "'s armor class? ";
    cin >> ac2; // armour class character 2

    // character 2 created
    Character character2(name2, role2, hp2, ab2, db2, ac2);
    // print summary
    character2.print(cout);

    // begin combat phase
    cout << "\nSimulating Combat:\n" << endl;

    /*====================================================================
    while character 1's health and character 2's is greater than 0, 
    character 1 will start the combat phase by attacking character 2
    and then once that is finished, character 2 will be able to attack if
    they have enough hit points remaining
    ====================================================================*/
    while (character1.getHealth() > 0 && character2.getHealth() > 0) {
        character1.attack(character2);

        if (character2.getHealth() > 0) {
            character2.attack(character1);
        }
    }

    /*====================================================================
    if character 1's health is greater than 0 and character 2's health
    is less than or equal to 0, character 1 wins, else, character 2 wins
    ====================================================================*/
    if (character1.getHealth() > 0 && character2.getHealth() <= 0) {
        cout << character1.getName() << " wins!\n";
    } else {
        cout << character2.getName() << " wins!\n";
    }

    return 0; // end of porgram
}

