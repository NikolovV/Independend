/*
 * Ensure a class has only one instance, and provide a global point of access to it.
 * Encapsulated "just-in-time initialization" or "initialization on first use".
 * Application needs one, and only one, instance of an object.
 * Additionally, lazy initialization and global access are necessary.
 * The advantage of Singleton over global variables is that you are absolutely sure
 * of the number of instances when you use Singleton, and, you can change your mind
 * and manage any number of instances.
 * Singleton pattern is used for logging, drivers objects, caching and thread pool.
 * Singleton design pattern is also used in other design patterns like
 * Abstract Factory, Builder, Prototype, Facade etc.
 * The Singleton pattern solves two problems at the same time, violating the Single Responsibility Principle
 */

#include <cstdlib>
#include <iostream>
#include <ratio>
#include <mutex>
#include <thread>

using namespace std;

class Singleton
{
public:
    int getValue() const
    {
        return value;
    }
    void SetValue(int value)
    {
        this->value = value;
    }
    static Singleton *getInstance()
    {
        if (!instance)
        {
            mt.lock();
            if (!instance)
            {
                instance = new Singleton();
            }
            mt.unlock();
        }
        return instance;
    }

private:
    Singleton()
    {
    };
    static Singleton *instance;
    static mutex mt;
    int value;
};

Singleton *Singleton::instance = nullptr;
mutex Singleton::mt;

void thrFunc(int x)
{
    Singleton *s = Singleton::getInstance();
    s->SetValue(s->getValue() + x);
    cout << "Thread " << x << " value: " << s->getValue() << endl;
}

int main(int argc, char** argv)
{
    Singleton *first = Singleton::getInstance();

    first->SetValue(5);
    cout << "value: " << first->getValue() << endl;

    Singleton *second = Singleton::getInstance();

    cout << "value: " << second->getValue() << endl;
    second->SetValue(546);
    cout << "value: " << second->getValue() << endl;

    cout << first << endl;
    cout << second << endl;

    thread t1[5];

    for (int i = 0; i < 5; i++)
    {
        t1[i] = thread(thrFunc, i);
    }

    for (int i = 0; i < 5; i++)
    {
        t1[i].join();
    }

    delete second;

    return 0;
}