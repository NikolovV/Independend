/* 
 * Composite pattern is used where we need to treat a group of objects in similar way as a single object.
 * Composite design pattern can be used to create a tree like structure 
 * and then work with these structures as if they were individual objects.
 *
 */

#include <cstdlib>
#include <iostream>
#include <list>
#include <memory>

using namespace std;

// Abstract class declare base functionality.
class ShoppingCart
{
public:
    virtual float getPrice() = 0;
    virtual string getInfo() = 0;
protected:
    string indent = "--";
};

// Specific implementation of end object "Leaf"
class Product : public ShoppingCart
{
public:
    Product(string name, float price, int level) :
    name(name), price(price), level(level)
    {
    }
    float getPrice() override
    {
        return this->price;
    };
    string getInfo() override
    {
        for (int i = 1; i < level; i++)
        {
            this->name.insert(0, indent);
        }

        return this->name + " \t- " + to_string(this->price);
    };

private:
    string name;
    float price;
    int level;
};

// Composition class holding the "Leaf", delegate the methods to inner component.
class PackBox : public ShoppingCart
{
public:
    PackBox(string name, float price, int level) :
    name(name), price(price), level(level)
    {
    }
    void addItemInBox(ShoppingCart &entity)
    {
        productBox.push_back(&entity);
    }
    float getPrice() override
    {
        for (auto elem : productBox)
        {
            price += elem->getPrice();
        }
        return this->price;
    };
    string getInfo() override
    {
        string strBuilder = this->name + " \t- " + to_string(this->price);
        for (int i = 1; i < level; i++)
        {
            strBuilder = this->indent + strBuilder;
        }

        for (auto elem : productBox)
        {
            strBuilder += "\n" + elem->getInfo();
        }
        
        return strBuilder;
    };

private:
    string name;
    float price;
    int level;
    list<ShoppingCart *> productBox;
};

int main(int argc, char** argv)
{
    Product beer = Product("beer", 2.60f, 3);
    Product glass = Product("glass", 0.5f, 3);

    PackBox baseBox = PackBox("baseBox", 0.0f, 2);
    baseBox.addItemInBox(beer);
    baseBox.addItemInBox(glass);

    PackBox giftBox = PackBox("giftBox", 0.20f, 1);
    giftBox.addItemInBox(baseBox);

    cout << giftBox.getInfo() << endl;
    cout << "Total: \t - " + to_string(giftBox.getPrice()) << endl;

    return 0;
}
