/*
 * Template Method is a behavioral design pattern and it’s used to create a method
 * stub and deferring some of the steps of implementation to the subclasses.
 * Template Method lets subclasses redefine certain steps of an algorithm
 * without changing the algorithm's structure.
 * Template method defines the steps to execute an algorithm and it can provide
 * a default implementation that might be common for all or some of the subclasses.
 * Most of the times, subclasses calls methods from super class but in template pattern,
 * superclass template method calls methods from subclasses, this is
 * known as Hollywood Principle – “don’t call us, we’ll call you.”.
 * Use the Template Method pattern when you want to let clients extend only
 * particular steps of an algorithm, but not the whole algorithm or its structure.
 * There’s another type of step, called hooks. A hook is an optional step with an empty body.
 * A template method would work even if a hook isn’t overridden.
 *
 * Cons: You might violate the Liskov Substitution Principle by suppressing
 * a default step implementation via a subclass.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

/*
 * The Abstract Class defines a template method that contains a skeleton of some
 * algorithm, composed of calls to (usually) abstract primitive operations.
 */
class DocumentGenerator
{
public:
    // The template method defines the skeleton of an algorithm.
    virtual void generateDocument() const final
    {
        cout << "\t\t\t" + header() + "\n" <<
                body() << "\n\n" <<
                signiture() << endl;
    }
protected:
    virtual string header() const = 0;
    virtual string body() const = 0;
private:
    string signiture() const
    {
        return "Company: ABC\nAddress: str. ABC N123\nSite: abc.com\n";
    }
};

/*
 * Concrete classes have to implement all abstract operations of the base class.
 * They can also override some operations with a default implementation.
 */
class Email : public DocumentGenerator
{
public:
    Email(string body) :
    bodyMsg(body)
    {
    }
    string header() const override
    {
        return "Email";
    };
    string body() const override
    {
        return this->bodyMsg;
    };
private:
    string bodyMsg;
};

class Letter : public DocumentGenerator
{
public:
    Letter(string body) :
    bodyMsg(body)
    {
    }
    string header() const override
    {
        return "Letter";
    };
    string body() const override
    {
        return this->bodyMsg;
    };
private:
    string bodyMsg;
};
int main(int argc, char** argv)
{
    string body = "Dear Mrs. Smit,\nThank you for the invitation.\n"
            "It will be oner to discuss our new partnership.";
    unique_ptr<DocumentGenerator> document(make_unique<Email>(body));
    document.get()->generateDocument();

    cout << "==================================" << endl;
    body = "Dear Ms. Lesly, \nWe would like to introduce you our new products.\n"
            "Please come to our presentation at str. ABC N123.";
    document = make_unique<Letter>(body);
    document.get()->generateDocument();


    return 0;
}