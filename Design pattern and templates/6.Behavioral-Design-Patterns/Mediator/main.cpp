/*
 * Mediator pattern is used to reduce communication complexity between multiple objects or classes.
 * The mediator design pattern is very helpful in an enterprise application
 * where multiple objects are interacting with each other.
 * If the objects interact with each other directly, the system components are
 * tightly coupled with each other that makes maintainability cost higher and not flexible to extend easily.
 * Mediator pattern focuses on to provide a mediator between objects for
 * communication and help in implementing lose-coupling between objects.
 */

#include <cstdlib>
#include <iostream>
#include <memory>
#include <ctime>
#include <future>
#include <sys/unistd.h>

using namespace std;
string logTime(char msgLevel = 'I')
{
    time_t sysdate = time(0);
    tm *dataTime = localtime(&sysdate);

    string logTimeFmt = "[" + to_string(1900 + dataTime->tm_year) + "-" +
            to_string(dataTime->tm_mon) + "-" +
            to_string(dataTime->tm_mday) + " " +
            to_string(dataTime->tm_hour) + ":" +
            to_string(dataTime->tm_min) + ":" +
            to_string(dataTime->tm_sec) + "] " + msgLevel + ": ";

    return logTimeFmt;
};

// Forward declaration of abstract Mediator
class Mediator;

/*
 * Base abstract class for the component that will be communicating through Mediator.
 * Inherit enable_shared_from_this to enable a common implementation for
 * enable_shared_from_this is to hold a weak reference (such as std::weak_ptr) to *this.
 */
class Vehicle : public enable_shared_from_this<Vehicle>
{
public:
    virtual void sendMsg(shared_ptr<Mediator> mediator, string message) = 0;
    virtual void receiveMsg(shared_ptr<Mediator> mediator, string message) = 0;
    virtual string getName() const = 0;
};

// Base abstract class for mediator through which communication will pass.

class Mediator : public enable_shared_from_this<Mediator>
{
public:
    Mediator(shared_ptr<Vehicle> comA, shared_ptr<Vehicle> comB) :
    comunicatorA(comA), comunicatorB(comB)
    {
    };
    virtual void notify(shared_ptr<Vehicle> communicator, string message) = 0;
    virtual string getName() = 0;
protected:
    shared_ptr<Vehicle> comunicatorA;
    shared_ptr<Vehicle> comunicatorB;
};

// Current implementation of Mediator that handle the notification.

class RadioTower : public Mediator
{
public:
    RadioTower(shared_ptr<Vehicle> comA, shared_ptr<Vehicle> comB, string name) :
    Mediator(comA, comB), name(name)
    {
    }
    void notify(shared_ptr<Vehicle> communicator, string message) override
    {
        // simulate latency
        sleep(1);
        cout << logTime('M') + this->name + " receive message form " +
                communicator.get()->getName() + "." << endl;

        if (communicator == this->comunicatorA)
        {
            cout << logTime('M') + this->name + " send message to receiver: " +
                    this->comunicatorB.get()->getName() << endl;
        }
        else
        {
            cout << logTime('M') + this->name + " send message to receiver: " +
                    this->comunicatorA.get()->getName() << endl;
        }
        (communicator == this->comunicatorA) ?
                this->comunicatorB.get()->receiveMsg(shared_from_this(), message) :
                this->comunicatorA.get()->receiveMsg(shared_from_this(), message);
    };
    string getName() override
    {
        return this->name;
    };

private:
    string name;
};

/*
 * Current implementation of component sending and receiving messages
 * to abstract / interface mediator injected in method.
 */
class AirLiner : public Vehicle
{
public:
    AirLiner(string name) :
    name(name)
    {
    }
    string getName() const
    {
        return name;
    }
    void sendMsg(shared_ptr<Mediator> mediator, string message) override
    {
        cout << logTime('S') + this->name + " send message to " +
                mediator.get()->getName() + " \"" + message + "\"" << endl;
        mediator.get()->notify(shared_from_this(), message);
    };
    void receiveMsg(shared_ptr<Mediator> mediator, string message) override
    {
        cout << logTime('R') + this->name + " receive message from " +
                mediator.get()->getName() + " \"" + message + "\"" << endl;
    };
    bool operator==(const AirLiner& obj) const
    {
        if (this->name.compare(obj.getName()) == 0)
        {
            true;
        }

        return false;
    }
private:
    string name;
};

class AirSaftyCar : public Vehicle
{
public:
    AirSaftyCar(string name) :
    name(name)
    {
    }
    string getName() const
    {
        return name;
    }
    void sendMsg(shared_ptr<Mediator> mediator, string message) override
    {
        cout << logTime('S') + this->name + " send message: " + "\"" + message + "\"" << endl;
        mediator.get()->notify(shared_from_this(), message);
    };
    void receiveMsg(shared_ptr<Mediator> mediator, string message) override
    {
        cout << logTime('R') + this->name + " receive message: " + "\"" + message + "\"" << endl;
    };
    bool operator==(const AirSaftyCar& obj) const
    {
        if (this->name.compare(obj.getName()))
        {
            true;
        }
        return false;
    }
private:
    string name;
};
int main(int argc, char** argv)
{
    shared_ptr<Vehicle> airLine = make_shared<AirLiner>("AirLiner");
    shared_ptr<Vehicle> airSaftyCar = make_shared<AirSaftyCar>("AirSaftyCar");

    shared_ptr<Mediator> radioTower = make_shared<RadioTower>(airLine, airSaftyCar, "RadioTower");

    airLine.get()->sendMsg(radioTower, "Hi there. Is a landing strip clear? Over.");
    cout << endl;
    airSaftyCar.get()->sendMsg(radioTower, "Hi there. We need some one minute. Over.");
    cout << endl;
    airLine.get()->sendMsg(radioTower, "OK. We will make one circle. Over.");
    cout << endl;
    airSaftyCar.get()->sendMsg(radioTower, "Landing strip is ready. Over.");

    return 0;
}