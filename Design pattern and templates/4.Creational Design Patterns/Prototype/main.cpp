/*
 * Prototype pattern is used when the Object creation is a costly affair and
 * requires a lot of time and resources and you have a similar object already existing.
 * So this pattern provides a mechanism to copy the original object to a
 * new object and then modify it according to our needs.
 * Prototype doesn't require subclassing, but it does require an "initialize" operation.
 * Cloning complex objects that have circular references might be very tricky.
 */

#include <cstdlib>
#include <iostream>

using namespace std;

class Shape
{
public:
    Shape()
    {
    }
    Shape(int numSide, string name) :
    numSide(numSide), name(name)
    {
    }
    virtual Shape * clone() = 0;
    void setName(string name)
    {
        this->name = name;
    }
    void setNumSide(int numSide)
    {
        this->numSide = numSide;
    }
    void showInfo()
    {
        cout << this->name << ": " << this->numSide << " sides" << endl;
    };
    virtual void draw() = 0;
    virtual ~Shape()
    {
    };
protected:
    int numSide;
    string name;
};

class Rectangle : public Shape
{
public:
    Rectangle() :
    Shape()
    {
    }

    Rectangle(int numSide, string name) :
    Shape(numSide, name)
    {
    }
    Shape* clone() override
    {
        return new Rectangle();
    };

    void draw() override
    {
        cout << "Draw Rectangle" << endl;
    };

};

class Square : public Shape
{
public:
    Square() :
    Shape()
    {
    }

    Square(int numSide, string name) :
    Shape(numSide, name)
    {
    }
    Shape* clone() override
    {
        return new Square();
    };

    void draw() override
    {
        cout << "Draw Square" << endl;
    };
};

int main(int argc, char** argv)
{
    Shape *sq = new Square(4, "Square");
    Shape *otherSq = sq->clone();

    otherSq->setName("Other Square");
    otherSq->setNumSide(6);

    sq->draw();
    sq->showInfo();

    otherSq->draw();
    otherSq->showInfo();

    delete sq;
    delete otherSq;

    return 0;
}

