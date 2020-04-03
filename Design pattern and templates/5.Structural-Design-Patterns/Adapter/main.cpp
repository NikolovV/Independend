/* 
 * Convert the interface of a class into another interface clients expect.
 * Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
 * Wrap an existing class with a new interface.
 * Impedance match an old component to a new system
 * Use the Adapter class when you want to use some existing class,
 * but its interface isn’t compatible with the rest of your code.
 * This approach looks very similar to the Decorator pattern.
 * 
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

enum ElectricityStandart
{
    EU = 2, US
};

// Class holding information about socket.
class Socket
{
public:
    Socket(ElectricityStandart elstandart)
    {
        switch (elstandart)
        {
        case US:
            holes = 3;
            break;
        case EU:
            holes = 2;
            break;
        }
    }
    void setHoles(int holes)
    {
        this->holes = holes;
    }

    virtual int getSocketHoles() const
    {
        return holes;
    }
    int getHoles() const
    {
        return holes;
    }

private:
    int holes;
};

// Abstract class for electric device and their functions.
class ElectricDevice
{
public:
    virtual void connect(Socket &socket) = 0;
    virtual int getPins() = 0;
    virtual ~ElectricDevice() = default;
};


// Adapter class helping connect to other type of sockets.
class SocketAdapter : public Socket
{
public:
    SocketAdapter(ElectricityStandart elstandart, std::shared_ptr<ElectricDevice> device) :
    Socket(elstandart), device(device)
    {
        this->convertHoles = getHoles();
    }

    int getSocketHoles() const override
    {
        if (convertHoles != device.get()->getPins())
        {
            cout << "That's not the right adapter for your device!" << endl;
        }
        return convertHoles;
    };

private:
    int convertHoles;
    std::shared_ptr<ElectricDevice> device;
};

// Current implementation of device.
class TV : public ElectricDevice
{
public:
    TV(int pins) :
    pins(pins)
    {
    }
    virtual ~TV()
    {
        cout << "Disconnect TV." << endl;
    }
    int getPins() override
    {
        return pins;
    };
    void connect(Socket& socket) override
    {
        if (socket.getSocketHoles() == this->pins)
        {
            start(socket);
        }
        else
        {
            cout << "TV need socket adapter from " << pins << " pins cord to " <<
                    socket.getHoles() << " holes socket!" << endl;
        }
    };
private:
    void start(Socket& socket)
    {
        cout << "TV is powered. Using " << pins << " pins cord and " <<
                socket.getHoles() << " hole socket." << endl;
    };
    int pins;
};

class HiFi : public ElectricDevice
{
public:
    HiFi(int pins) :
    pins(pins)
    {
    }
    virtual ~HiFi()
    {
        cout << "Disconnect HiFi." << endl;
    }
    int getPins() override
    {
        return pins;
    };
    void connect(Socket& socket) override
    {
        if (socket.getSocketHoles() == this->pins)
        {
            start(socket);
        }
        else
        {
            cout << "HiFi need socket adapter from " << pins << " pins cord to " <<
                    socket.getHoles() << " holes socket!" << endl;
        }
    };
private:
    void start(Socket& socket)
    {
        cout << "HiFi is powered. Using " << pins << " pins cord and " <<
                socket.getHoles() << " hole socket." << endl;
    };
    int pins;
};

// Client code usage.
void clientUse(shared_ptr<ElectricDevice> device, Socket &socket)
{
    device.get()->connect(socket);
}

// Test functions.
void test1()
{
    cout << "====================== Test 1 ======================" << endl;
    Socket socket = Socket(US);
    std::shared_ptr<ElectricDevice> device = make_shared<TV>(2);

    // Set the right ElectricityStandart for adapter to convert.
    SocketAdapter saEU = SocketAdapter(EU, device);

    clientUse(device, socket);

    cout << "\nUsing Adapter design pattern..." << endl;
    clientUse(device, saEU);
    cout << "====================== End Test 1 ======================" << endl;
}
void test2()
{
    cout << "====================== Test 2 ======================" << endl;
    Socket socket = Socket(US);
    std::shared_ptr<ElectricDevice> device = make_shared<HiFi>(3);

    // Set the right ElectricityStandart for adapter to convert.
    SocketAdapter saEU = SocketAdapter(EU, device);
    SocketAdapter saUS = SocketAdapter(US, device);

    clientUse(device, socket);

    cout << "\nUsing incompatible adapter design pattern..." << endl;
    clientUse(device, saEU);
    cout << "\nUsing the right Adapter..." << endl;
    clientUse(device, saUS);
    cout << "====================== End Test 2 ======================" << endl;
}

int main(int argc, char** argv)
{
    test1();
    test2();
 
    return 0;
}