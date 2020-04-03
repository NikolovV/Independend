/* 
 * Decorator is a structural design pattern that lets you attach new behaviors
 * to objects by placing these objects inside special wrapper objects that contain the behaviors.
 * At the same time, other instances of the same class will not be affected by this,
 * so individual object gets the modified behavior. Uses abstract classes or
 * interface with the composition to implement.
 * Attach additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 *
 * Use the Decorator pattern when you need to be able to assign extra behaviors
 * to objects at runtime without breaking the code that uses these objects.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

// Abstract class for base functionality.
class Weapon
{
public:
    virtual void fire() = 0;
};

// Current implementation of base functionality.
class Pistol : public Weapon
{
public:
    void fire() override
    {
        cout << "Pistol fire." << endl;
    };
};

// Current implementation of base functionality.
class Sniper : public Weapon
{
public:
    void fire() override
    {
        cout << "Sniper fire." << endl;
    };
};

// Decorator abstract class implement and allows extend the base functionality.
class Accesory : public Weapon
{
public:
    Accesory(shared_ptr<Weapon> weapon) :
    weapon(weapon)
    {
    }
    void fire()
    {
        weapon.get()->fire();
    };
private:
    shared_ptr<Weapon> weapon;
};

// Extend base functionality of the decorator.
class FlushLight : public Accesory
{
public:
    FlushLight(shared_ptr<Weapon> weapon) :
    Accesory(weapon)
    {
    }

    void fire() override
    {
        cout << this->light;
        Accesory::fire();
    };
private:
    string light = "Light the enemy with Flush Light ";
};

class Optic : public Accesory
{
public:
    Optic(shared_ptr<Weapon> weapon) :
    Accesory(weapon)
    {
    }

    void fire() override
    {
        cout << this->scopeType;
        Accesory::fire();
    };
private:
    string scopeType = "Zoom the enemy with doted Optic ";
};

int main(int argc, char** argv)
{
    shared_ptr<Weapon> pistol = make_shared<Pistol>();
    pistol.get()->fire();

    unique_ptr<Accesory> pistolFlush = make_unique<FlushLight>(pistol);
    pistolFlush.get()->fire();

    unique_ptr<Weapon> sniper = make_unique<Optic>(make_shared<Sniper>());
    sniper.get()->fire();

    return 0;
}