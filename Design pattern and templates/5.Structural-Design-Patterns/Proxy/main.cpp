/* 
 * Proxy pattern intent is to “Provide a surrogate or placeholder for another object to control access to it”.
 * A proxy controls access to the original object, allowing you to perform
 * something either before or after the request gets through to the original object.
 * You could implement lazy initialization: create this object only when it’s actually needed.
 */

#include <cstdlib>
#include <iostream>
#include <memory>
#include <sys/unistd.h>

using namespace std;

// Interface of sensitive master object
class Router
{
public:
    virtual void connect(string passowrd) = 0;
};

// Sensitive master object
class WiFiRouter : public Router
{
public:
    
    WiFiRouter(string ssid) :
    ssid(ssid)
    {
    }

    void connect(string passowrd) override
    {
        cout << "WiFi Router connected with " + this->ssid << endl;
    };

private:
    string ssid;
};

/*
 * Proxy pattern with the same interface as an original service object.
 * Upon receiving a request from a client, the proxy creates a real service object
 * and delegates all the work to it.
 */
class ProxyRouter : public Router
{
public:
    ProxyRouter(string ssid) :
    ssid(ssid)
    {
        cout << "Default password is set. Change it!" << endl;
        this->connectionPass = "default";
    }
    void connect(string password) override
    {
        router = make_shared<WiFiRouter>(this->ssid);

        cout << "Trying to connect..." << endl;
        sleep(1);

        if (password.compare(this->connectionPass) == 0)
        {
            this->router.get()->connect(password);
        }
        else
        {
            cout << "Wrong password!" << endl;
        }
    };
    void setConnectionPass(string user, string password, string newPassword)
    {
        // This may be get from DB or file or another security layer.
        if (user.compare("Pesho") == 0 and password.compare("StrongPa$$w0r9"))
        {
            this->connectionPass = newPassword;
        }
        else
        {
            cout << "No permission." << endl;
        }
    }

private:
    shared_ptr<Router> router;
    string ssid;
    string connectionPass;
};

int main(int argc, char** argv)
{
    ProxyRouter rt = ProxyRouter("homeWiFi");
    rt.connect("weekPasss");

    cout << "\nTry to change password.\n";
    rt.setConnectionPass("go6o", "goshoPi4a", "stroNgPa$$w0rd");
    rt.connect("stroNgPa$$w0rd");

    cout << "\nAdmin set password.\n";
    rt.setConnectionPass("Pesho", "Pe60StrongPa$$w0r9", "stroNgW1F1Pa$$w0rd");
    rt.connect("stroNgW1F1Pa$$w0rd");

    return 0;
}