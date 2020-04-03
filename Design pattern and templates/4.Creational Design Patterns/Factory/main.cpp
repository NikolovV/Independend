/*
 * Define an interface for creating an object, but let subclasses decide
 * which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
 * The advantage of a Factory Method is that it can return the same instance
 * multiple times, or can return a subclass rather than an object of that exact type.
 * Use the Factory Method when you want to save system resources by
 * reusing existing objects instead of rebuilding them each time.
 * You often experience this need when dealing with large, resource-intensive objects
 * such as database connections, file systems, and network resources.
 * Many designs start by using Factory Method (less complicated and more customizable
 * via subclasses) and evolve toward Abstract Factory, Prototype, or Builder (more flexible, but more complicated).
 */

#include <cstdlib>
#include <iostream>

using namespace std;

// Base class for objects
class Shape
{
public:
    virtual void draw() = 0;
};

// Specific object
class Circle : public Shape
{
public:
    void draw()
    {
        cout << "Drawing Circle" << endl;
    }
};

class Square : public Shape
{
public:
    void draw()
    {
        cout << "Drawing Square" << endl;
    }
};

// Factory class for creating sub-classes objects based on input data.
class ShapeFactory
{
public:
    Shape *getShape(string shapeType)
    {
        if (shapeType.compare("Circle"))
        {
            return new Circle();
        }
        else if (shapeType.compare("Square"))
        {
            return new Square();
        }

        return NULL;
    }
};

int main(int argc, char** argv)
{
    ShapeFactory shapeFactory = ShapeFactory();

    Shape * cir = shapeFactory.getShape("Circle");
    cir->draw();
    delete cir;

    Shape *sq = shapeFactory.getShape("Square");
    sq->draw();
    delete sq;

    return 0;
}

