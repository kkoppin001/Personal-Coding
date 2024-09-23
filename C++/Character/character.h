#include <string>
#include <iostream>

using namespace std;

class Character {

/*====================================================================
PRIAVTE VARIABLE DECLARATIONS
====================================================================*/
private:
    string name;
    string role;
    int hitPoints;
    int attackBonus;
    int damageBonus;
    int armorClass;

/*====================================================================
PUBLIC INFORMATION & FUNCTIONS
====================================================================*/
public:
    // constructor
    Character(string name, string role, int hp, int ab, int db, int ac);

    // print character details
    void print(ostream &out);

    // attack another character
    void attack(Character &otherCharacter);

    // subtract hit points from the character
    void damage(int amount);

    // getter functions
    int getHealth() const;
    string getName() const;
    string getRole() const;
};

