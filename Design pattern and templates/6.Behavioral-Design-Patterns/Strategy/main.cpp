/*
 * In Strategy pattern, a class behavior or its algorithm can be changed at run time.
 * In Strategy pattern, we create objects which represent various strategies and
 * a context object whose behavior varies as per its strategy object.
 * Strategy pattern is also known as Policy Pattern.
 * We define multiple algorithms and let client application pass the algorithm
 * to be used as a parameter.
 * Strategy Pattern is very similar to State Pattern. One of the difference is
 * that Context contains state as instance variable and there can be multiple tasks
 * whose implementation can be dependent on the state whereas in strategy pattern
 * strategy is passed as argument to the method and context object doesn’t have any variable to store it.
 * Strategy pattern is useful when we have multiple algorithms for specific task
 * and we want our application to be flexible to chose any of the algorithm at runtime for specific task.
 * Cons: A lot of modern programming languages have functional type support that lets you
 * implement different versions of an algorithm inside a set of anonymous functions.
 * Then you could use these functions exactly as you’d have used the strategy objects,
 * but without bloating your code with extra classes and interfaces.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

/*
 * The Strategy interface declares operations common to all supported versions
 * of some algorithm.
 */
class Vehicle
{
public:
    virtual string transport() = 0;
};

/*
 * The Context defines the interface of interest to clients and delegates
 * some work to the Strategy object instead of implementing multiple versions
 * of the algorithm on its own.
 */
class TravelAgency
{
public:
    TravelAgency(int tourist) :
    tourist(tourist)
    {
    }
    string transportTourist(shared_ptr<Vehicle> vehicle)
    {
        return vehicle.get()->transport();
    }
    int getTourist() const
    {
        return tourist;
    }
private:
    int tourist;
};

/*
 * Concrete Strategies implement the algorithm while following the base
 * Strategy interface. The interface makes them interchangeable in the Context.
 */
class Car : public Vehicle
{
public:
    Car(int maxPassenger) :
    maxPassenger(maxPassenger)
    {
    }
    string transport() override
    {
        string buffer = "I can take up to " + to_string(this->maxPassenger) +
                " passengers ( 1 driver + " + to_string(this->maxPassenger - 1) +
                " tourist).\n";
        return buffer;
    };
private:
    int maxPassenger;
};

class Van : public Vehicle
{
public:
    Van(int maxPassenger) :
    maxPassenger(maxPassenger)
    {
    }
    string transport() override
    {
        string buffer = "I can take up to " + to_string(this->maxPassenger) +
                " passengers ( 1 driver + " + to_string(this->maxPassenger - 1) +
                " tourist).\n";
        return buffer;
    };
private:
    int maxPassenger;
};

class Bus : public Vehicle
{
public:
    Bus(int maxPassenger) :
    maxPassenger(maxPassenger)
    {
    }
    string transport() override
    {
        string buffer = "I can take up to " + to_string(this->maxPassenger) +
                " passengers ( 2 drivers + " + to_string(this->maxPassenger - 2) +
                " tourist).\n";
        return buffer;
    };
private:
    int maxPassenger;
};
int main(int argc, char** argv)
{
    TravelAgency travelAgency(18);
    if (travelAgency.getTourist() <= 5)
    {
        cout << travelAgency.transportTourist(make_shared<Car>(5));
    }
    else if (travelAgency.getTourist() > 5 && travelAgency.getTourist() < 16)
    {
        cout << travelAgency.transportTourist(make_shared<Van>(16));
    }
    else if (travelAgency.getTourist() > 16)
    {
        cout << travelAgency.transportTourist(make_shared<Bus>(50));
    }

    return 0;
}