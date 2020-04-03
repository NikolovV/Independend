/*
 * State design pattern is used when an Object change it’s behavior based on it’s internal state.
 * The State pattern suggests that you create new classes for all possible states
 * of an object and extract all state-specific behaviors into these classes.
 * The original object, called context, stores a reference to one of the
 * state objects that represents its current state, and delegates all the state-related work to that object.
 * State pattern is used to provide a systematic
 * and loosely coupled way to achieve this through Context and State implementations.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

/*
 * The base State class declares methods that all Concrete State should
 * implement and also provides a backreference to the Context object, associated
 * with the State.
 */
class State
{
public:
    State() = default;
    virtual ~State() = default;
    virtual string on(class ElectricalDevice *device) = 0;
    virtual string off(ElectricalDevice *device) = 0;
    virtual int changeRotation(ElectricalDevice *device) = 0;
protected:
    bool isOn;
    int rotationSpeed;
};

/*
 * The Context defines the interface of interest to clients. It also maintains a
 * reference to an instance of a State subclass, which represents the current
 * state of the Context.
 */
class ElectricalDevice
{
public:
    ElectricalDevice(shared_ptr<State> state) : state(state)
    {
    };
    virtual ~ElectricalDevice() = default;
    virtual string on() = 0;
    virtual string off() = 0;
    virtual int changeRotation() = 0;

    // The Context allows changing the State object at runtime.
    virtual void setState(shared_ptr<State> state)
    {
        this->state = state;
    };
protected:
    shared_ptr<State> state;
};

class Fan : public ElectricalDevice
{
public:
    Fan(shared_ptr<State> state);
    ~Fan()
    {
        //        cout << "Fan destructor." << endl;
    }
    string on() override
    {
        return this->state.get()->on(this);
    };
    string off() override
    {
        return this->state.get()->off(this);
    };
    int changeRotation() override
    {
        return this->state.get()->changeRotation(this);
    };
};

/*
 * Concrete States implement various behaviors, associated with a state of the
 * Context.
 */
class Working : public State
{
public:
    Working() :
    State()
    {
        //        cout << "Working constructor." << endl;
        this->isOn = true;
        this->rotationSpeed = 0;
    }
    ~Working()
    {
        //        cout << "Working destructor." << endl;
    };
    string on(ElectricalDevice* device) override
    {
        if (this->isOn)
        {
            return "Device is already ON.\n";
        }
        return "";
    }
    string off(ElectricalDevice* device);
    int changeRotation(ElectricalDevice* device) override
    {
        if (this->rotationSpeed >= 0 && this->rotationSpeed < 5)
        {
            return ++this->rotationSpeed;
        }
        return this->rotationSpeed = 1;
    };
};

class NotWorking : public State
{
public:
    NotWorking() :
    State()
    {
        //        cout << "Notworkin constructor." << endl;
        this->isOn = false;
        this->rotationSpeed = 0;
    }
    ~NotWorking()
    {
        //        cout << "Noworking destructor." << endl;
    };
    string on(ElectricalDevice* device) override
    {
        if (this->isOn)
        {
            return "The device is already ON.\n";
        }
        device->setState(make_shared<Working>());
        return "Turning device ON.\n";
    };
    string off(ElectricalDevice* device) override
    {
        if (this->isOn)
        {
            this->isOn = false;
            this->rotationSpeed = 0;
            return "Turning device OFF.\n";
        }
        return "The device is already OFF.\n";
    };
    int changeRotation(ElectricalDevice* device) override
    {
        if (this->isOn)
        {
            if (this->rotationSpeed >= 0 && this->rotationSpeed < 5)
            {
                return ++this->rotationSpeed;
            }
            else
            {
                return this->rotationSpeed = 1;
            }
        }
        cout << "Device is OFF. ";
        return this->rotationSpeed = 0;
    };
};
string Working::off(ElectricalDevice* device)
{
    if (this->isOn)
    {
        device->setState(make_shared<NotWorking>());
        return "Turning device OFF.\n";
    }
    return "The device is already OFF.\n";
}
Fan::Fan(shared_ptr<State> state) :
ElectricalDevice(state)
{
    //    cout << "Fan constructor." << endl;
}
int main(int argc, char** argv)
{
    shared_ptr<ElectricalDevice> device = make_shared<Fan>(make_shared<NotWorking>());
    cout << device.get()->on();
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->on();
    cout << device.get()->off();
    cout << device.get()->changeRotation() << endl;
    cout << device.get()->off();

    return 0;
}