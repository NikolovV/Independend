/*
 * Chain of responsibility pattern is used to achieve loose coupling in software design
 * where a request from the client is passed to a chain of objects to process them.
 * Then the object in the chain will decide themselves who will be processing the request
 * and whether the request is required to be sent to the next object in the chain or not.
 * Client doesn’t know which part of the chain will be processing the request and
 * it will send the request to the first object in the chain
 */

#include <cstdlib>
#include <iostream>
#include <memory>
#include <sys/unistd.h>

using namespace std;

// Interface of main functionality. Include method for setting the chain of responsibility.

class CallCenter
{
public:
    virtual shared_ptr<CallCenter> setOnChain(shared_ptr<CallCenter> cc) = 0;
    virtual string response(string request) = 0;
};

// Abstract class implementing chain of responsibility.

class HandlerSupport : public CallCenter
{
public:
    HandlerSupport() : ccHandler(0)
    {
    }
    shared_ptr<CallCenter> setOnChain(shared_ptr<CallCenter> cc) override
    {
        this->ccHandler = cc;
        return cc;
    };
    string response(string request) override
    {
        if (this->ccHandler)
        {
            return this->ccHandler.get()->response(request);
        }

        return {};
    };
private:
    shared_ptr<CallCenter> ccHandler;
};

// Concrete level of support processed the request or pass it to next level.

class NetworkSupport : public HandlerSupport
{
public:
    string response(string request) override
    {
        cout << "NetworkSupport processing the request ... " + request << endl;
        sleep(1);
        if (request.find("network") != std::string::npos)
        {
            return "Try to restart the router.";
        }
        else
        {
            cout << "NetworkSupport can't solve the request. Send to next level." << endl;
            return HandlerSupport::response(request);
        }
    };

};

// Concrete level of support processed the request or pass it to next level.

class HardwareSupport : public HandlerSupport
{
public:
    string response(string request) override
    {
        cout << "HardwareSupport processing the request ... " + request << endl;
        sleep(1);
        if (request.find("hardware") != std::string::npos)
        {
            return "Try to restart the device.";
        }
        else
        {
            cout << "HardwareSupport can't solve the request. Send to next level." << endl;
            return HandlerSupport::response(request);
        }
    };
};

// Concrete level of support processed the request or pass it to next level.

class BusinessSupport : public HandlerSupport
{
public:
    string response(string request) override
    {
        cout << "BusinessSupport processing the request ... " + request << endl;
        sleep(1);
        if (request.find("business") != std::string::npos)
        {
            return "Read the contract.";
        }
        else
        {
            return "Please visit out main office for support.";
        }
    };
};
/*
 * Client code call for support.
 * Client is not even aware that the request is part of a chain.
 */
void clientChain(shared_ptr<CallCenter> cc, int requstCnt = 1)
{
    cout << "\t" + cc.get()->response("Hi i've problem with network!!") << endl;

    cout << endl;
    cout << "\t" + cc.get()->response("Hi my hardware device doesn't work!!") << endl;

    cout << endl;
    cout << "\t" + cc.get()->response("Hi the device is not compatible with our business process!!") << endl;

    cout << endl;
    cout << "\t" + cc.get()->response("Hi there is disturbing clause in contract!!") << endl;
}
int main(int argc, char** argv)
{
    shared_ptr<CallCenter> network = make_shared<NetworkSupport>();
    shared_ptr<CallCenter> hardware = make_shared<HardwareSupport>();
    shared_ptr<CallCenter> business = make_shared<BusinessSupport>();

    // Be careffull when setting chain!
    network.get()->setOnChain(hardware).get()->setOnChain(business);

    clientChain(network);

    return 0;
}