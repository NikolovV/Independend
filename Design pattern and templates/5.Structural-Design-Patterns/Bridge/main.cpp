/* 
 * Bridge is a structural design pattern that lets you split a large class or a
 * set of closely related classes into two separate hierarchies—abstraction and
 * implementation—which can be developed independently of each other.
 * The implementation of bridge design pattern follows the notion to prefer Composition over inheritance.
 * Adapter makes things work after they're designed; Bridge makes them work before they are.
 * Abstract base class use "has-a" relationship.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

// Base abstract class for extendable weapon types.
class Weapon
{
public:
    Weapon(int value, string weapon) :
    damagePoint(value), weaponType(weapon)
    {
        weaponType[0] = toupper(weaponType[0]);
    }
    virtual void damage() = 0;
    virtual ~Weapon()
    {
        cout << "Weapon cleaning..." << endl;
    }
protected:
    int damagePoint;
    string weaponType;
};

class Axe : public Weapon
{
public:
    Axe(int value, string weapon) :
    Weapon(value, weapon)
    {
    }
    void damage() override
    {
        cout << "Hitting target - Weapon: " + this->weaponType + " : Damage: " + to_string(damagePoint) + ".\n";
    };
};

// Add new specific weapon.

class Sword : public Weapon
{
public:
    Sword(int value, string weapon) :
    Weapon(value, weapon)
    {
    }
    void damage() override
    {
        cout << "Hitting target - Weapon: " + this->weaponType + " : Damage: " + to_string(damagePoint) + ".\n";
    };
};

/* Base abstract class for extendable character types.
 * Use property Weapon as "Bridge" between two abstraction class Character and Weapon.
 */
class Character
{
public:
    Character(shared_ptr<Weapon> &weapon, string characterType) :
    weapon(weapon), characterType(characterType)
    {
        this->characterType[0] = toupper(characterType[0]);
    }
    virtual void attack() = 0;
    virtual ~Character()
    {
        cout << "Character cleaning..." << endl;
    }
    void changeWeapon(shared_ptr<Weapon> &weapon)
    {
        this->weapon = weapon;
    }
protected:
    shared_ptr<Weapon> weapon;
    string characterType;
};

class Orc : public Character
{
public:
    Orc(shared_ptr<Weapon> &weapon, string characterType) :
    Character(weapon, characterType)
    {
    }

    void attack() override
    {
        cout << this->characterType + " attacking:" << endl;
        weapon.get()->damage();
    };
};

// Add new character.

class Elf : public Character
{
public:
    Elf(shared_ptr<Weapon> &weapon, string characterType) :
    Character(weapon, characterType)
    {
    }
    void attack() override
    {
        cout << this->characterType + " attacking:" << endl;
        weapon.get()->damage();
    };
};


int main(int argc, char** argv)
{
    cout << "============== Test 1 ==============" << endl;
    shared_ptr<Weapon> axe = make_shared<Axe>(25, "axe");

    unique_ptr<Character> orc = make_unique<Orc>(axe, "orc");
    orc.get()->attack();

    cout << "============== End Test 1 ==============" << endl;
    cout << "\n";

    // Testing newly added weapon and character.
    cout << "============== Test 2 ==============" << endl;
    shared_ptr<Weapon> sword = make_shared<Sword>(60, "sword");

    unique_ptr<Character> elf = make_unique<Elf>(sword, "elf");
    elf.get()->attack();

    cout << "Elf switching weapon." << endl;
    elf.get()->changeWeapon(axe);
    elf.get()->attack();

    cout << "============== End Test 2 ==============" << endl;


    return 0;
}