#include "Character.h"
#include <cstdlib> // for rand()

using namespace std;

// constructor definition
Character::Character(string name, string role, int hp, int ab, int db, int ac)
    : name(name), role(role), hitPoints(hp), attackBonus(ab), damageBonus(db), armorClass(ac) {}

// print character summary
void Character::print(ostream &out) {
    out << "\nCharacter Summary:\n" 
        << "-------------------\n"
        << name << " the " << role << "\n"
        << "HP: " << hitPoints << "\n"
        << "Attack Bonus: " << attackBonus << "\n"
        << "Damage Bonus: " << damageBonus << "\n"
        << "Armor Class: " << armorClass << "\n";
}

void Character::attack(Character &otherCharacter) {

/*====================================================================
 VARIABLE DECLARATIONS
====================================================================*/
    int attackRoll;
    int totalAttack;
    int damageRoll;
    int totalDamage;


/*====================================================================
 The attack roll will be a "20 sided" die and the total
 attack will be the roll plus the attack bonus
====================================================================*/
    attackRoll = rand() % 20 + 1; 
    totalAttack = attackRoll + attackBonus;
    cout << name << " attacks!\n";
    cout << "Attack roll: " << attackRoll << " + " << attackBonus << " = " << totalAttack << " --> ";


/*====================================================================
If the total attack is greater or equal to a character's armour class, 
it will be a hit and a 10 sided die will be rolled for total damage
If not, the attack will be a miss
====================================================================*/
    if (totalAttack >= otherCharacter.armorClass) {
        
        damageRoll = rand() % 10 + 1;  
        totalDamage = damageRoll + damageBonus;
        cout << "HIT!\n";
        cout << "Damage: " << damageRoll << " + " << damageBonus << " = " << totalDamage << "\n";
        otherCharacter.damage(totalDamage);
        cout << otherCharacter.getName() << " has " << otherCharacter.getHealth() << " hit points remaining.\n";
    
    } else {
        cout << "MISS!\n";
    }
}

// subtract hit points from the character
void Character::damage(int amount) {
    hitPoints -= amount;
    if (hitPoints < 0) {
        hitPoints = 0;
    }
}

/*====================================================================
GETTER METHODS
====================================================================*/
int Character::getHealth() const {
    return hitPoints;
}

string Character::getName() const {
    return name;
}

string Character::getRole() const {
    return role;
}

