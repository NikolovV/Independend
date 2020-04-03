/* 
 * Facade pattern hides the complexities of the system and provides an interface
 * to the client using which the client can access the system.
 * Wrap a complicated subsystem with a simpler interface.
 * Instead of making your code work with dozens of the framework classes directly,
 * you create a facade class which encapsulates that functionality and hides it from the rest of the code.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

using namespace std;

// Base abstract class for device connectivity type
class Connection
{
public:
    virtual string connect() = 0;
};

// Implementation of specific connection
class Bluetooth : public Connection
{
public:
    Bluetooth(string conName) :
    name(conName)
    {
    }
    string connect() override
    {
        return this->name;
    };

private:
    string name;
};

// Implementation of specific connection
class Wired : public Connection
{
public:
    Wired(string name) :
    name(name)
    {
    }
    string connect() override
    {
        return this->name;
    };

private:
    string name;
};

// Base abstract class for device functionality.
class Function
{
public:
    virtual string play() = 0;
    virtual string stop() = 0;
    virtual string next() = 0;
    virtual string previous() = 0;
    Function(shared_ptr<Connection> connection)
    {
    };

protected:
    shared_ptr<Connection> connection;
};

// Specific implementation of device functions.
class InEarHeadPhone : public Function
{
public:
    InEarHeadPhone(shared_ptr<Connection> connection, string type) :
    Function(connection), type(type)
    {
        cout << this->type + " connected by " + connection.get()->connect() << endl;
    }

    string next() override
    {
        return this->type + " Next button pushed";
    };
    string play() override
    {
        return this->type + " Play button pushed";
    };
    string previous() override
    {
        return this->type + " Previous button pushed";
    };
    string stop() override
    {
        return this->type + " Stop button pushed";
    };
private:
    string type;
};

// Specific implementation of device functions.
class Handsfree : public Function
{
public:
    Handsfree(shared_ptr<Connection> connection, string type) :
    Function(connection), type(type)
    {
        cout << this->type + " connected by " + connection.get()->connect() << endl;
    }
    string next() override
    {
        return this->type + " Next button pushed";
    };
    string play() override
    {
        return this->type + " Play button pushed";
    };
    string previous() override
    {
        return this->type + " Previous button pushed";
    };
    string stop() override
    {
        return this->type + " Stop button pushed";
    };
private:
    string type;
};

/*
 * Facade class.
 * Base device type witch provide simple user interface that delegate most of
 * work to other classes of the framework and retrieving result in a correct format.
 */
class HeadPhone
{
public:
    HeadPhone(shared_ptr<Function> device) :
    device(device)
    {
    }
    string nextSong()
    {
        return this->device.get()->next() + " - next song";
    }
    string previousSong()
    {
        return this->device.get()->previous() + " - previous song";
    }
    string nextPlayList()
    {
        return "Next play list with double click next button - " + this->device.get()->next() + " | " +
                this->device.get()->next();
    }
    string answerCall()
    {
        cout << "Push and hold play button for 3 second ..." << endl;
        sleep(3);
        return this->device.get()->play() + " - for 3 seconds - answer call.";
    }
    
private:
    shared_ptr<Function> device;
};

int main(int argc, char** argv)
{
    HeadPhone hf = HeadPhone(make_shared<Handsfree>(make_shared<Bluetooth>("bluetooth"), "HandsFree"));
    cout << hf.nextSong() << endl;
    cout << "Receive call from Pesho..." << endl;
    cout << hf.answerCall() << endl;
    cout << hf.nextPlayList() << endl;

    cout << endl;

    HeadPhone ih = HeadPhone(make_shared<InEarHeadPhone>(make_shared<Wired>("wire"), "In ear headphones"));
    cout << ih.nextSong() << endl;
    cout << ih.nextPlayList() << endl;
    cout << "Receive call from Penka..." << endl;
    cout << ih.answerCall() << endl;
    cout << ih.previousSong() << endl;

    return 0;
}