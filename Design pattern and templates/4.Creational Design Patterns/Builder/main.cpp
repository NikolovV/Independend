/*
 * Separate the construction of a complex object from its representation
 * so that the same construction process can create different representations.
 * Parse a complex representation, create one of several targets.
 * 
 * Builder returns the product as a final step, but as far as the
 * Abstract Factory is concerned, the product gets returned immediately.
 */

#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

// Define packing Interface
class Packing
{
public:
    virtual string pack() = 0;
    virtual ~Packing()
    {
    }
};

// Define meal menu Item interface
class Item
{
public:
    virtual string name() = 0;
    virtual Packing *packing() = 0;
    virtual float price() = 0;
    virtual ~Item()
    {
    }
};

// Current pack type
class Wrapper : public Packing
{
public:
    string pack() override
    {
        return "Wrapper";
    };
};

class Bottle : public Packing
{
public:
    string pack() override
    {
        return "Bottle";
    };
};

// Abstract Burger type
class Burger : public Item
{
public:
    Packing* packing() override
    {
        return new Wrapper();
    };

    //virtual float price() = 0;
    virtual ~Burger()
    {
    };
};

// Abstract Drink type
class Drink : public Item
{
public:
    Packing *packing()
    {
        return new Bottle();
    };
    virtual ~Drink()
    {
    };
};

class VegyBurger : public Burger
{
public:
    string name() override
    {
        return "Vegy Burger";
    };
    float price() override
    {
        return 10.50f;
    };
    virtual ~VegyBurger()
    {
    };
};

class BeefBurger : public Burger
{
public:
    string name() override
    {
        return "Beef Burger";
    };
    float price() override
    {
        return 8.50f;
    };
    virtual ~BeefBurger()
    {
    };
};

class Coke : public Drink
{
public:
    string name() override
    {
        return "Coke";
    };
    float price() override
    {
        return 2.20f;
    };
};

class Water : public Drink
{
public:
    string name() override
    {
        return "Water";
    };
    float price() override
    {
        return 1.20f;
    };
};

/* Unlike other creational patterns, Builder can construct unrelated products,
 * which don't have the common interface.
 */
class MealMenu
{
public:
    void addItem(Item *item)
    {
        items.push_back(item);
        pack = item->packing();
    };

    float getCost()
    {
        float cost = 0.0f;

        for (auto it : items)
        {
            cost += it->price();
        }
        return cost;
    }
    void showItem()
    {
        for (auto it : items)
        {
            cout << "Item: " << it->name() << endl;
            cout << "Packing: " << pack->pack() << endl;
            cout << "Price: " << it->price() << endl;
        }
        cout << "Total: " << this->getCost() << endl;
    };
    ~MealMenu()
    {
        cout << "Cleaning the items..." << endl;
        for (auto it : items)
        {
            delete it;
        }
        delete pack;
    };
private:
    vector<Item *> items;
    Packing *pack;
};

// Director defines the order of building steps.
class MealDirector
{
public:
    MealMenu veganMenu()
    {
        MealMenu veg = MealMenu();
        veg.addItem(new VegyBurger());
        veg.addItem(new Water());
        return veg;
    };

    MealMenu meetMenu()
    {
        MealMenu meet = MealMenu();
        meet.addItem(new BeefBurger());
        meet.addItem(new Coke());
        return meet;
    }
    ~MealDirector()
    {
    };
};

int main(int argc, char** argv)
{
    MealDirector builder = MealDirector();

    MealMenu meet = builder.meetMenu();
    meet.showItem();

    cout << "============ New order ============" << endl;
    
    MealMenu vegy = builder.veganMenu();
    vegy.showItem();

    return 0;
}

