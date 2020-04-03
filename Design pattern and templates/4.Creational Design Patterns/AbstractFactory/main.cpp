/*
 * Abstract Factory pattern is similar to Factory pattern and it’s a factory of factories.
 * In Abstract Factory pattern, we get rid of if-else block and have a factory class
 * for each sub-class and then an Abstract Factory class that will return the sub-class based on the input factory class.
 */

#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

// Base class for all type PC
class Computer
{
public:
    virtual void showInfo() = 0;
private:
    string RAM;
    string HDD;
    string CPU;
};

class HomePC : public Computer
{
public:

    HomePC(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };
    void showInfo() override
    {
        cout << "System config HomePC: " <<
                this->RAM << "; " <<
                this->HDD << "; " <<
                this->CPU << "; " << endl;
    };

private:
    string RAM;
    string HDD;
    string CPU;

    string getCPU() const
    {
        return CPU;
    }

    string getHDD() const
    {
        return HDD;
    }

    string getRAM() const
    {
        return RAM;
    }
};

class ServerPC : public Computer
{
public:

    ServerPC(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };

    void showInfo() override
    {
        cout << "System config Server: " <<
                this->RAM << "; " <<
                this->HDD << "; " <<
                this->CPU << "; " << endl;
    };
private:
    string RAM;
    string HDD;
    string CPU;

    string getCPU() const
    {
        return CPU;
    }

    string getHDD() const
    {
        return HDD;
    }

    string getRAM() const
    {
        return RAM;
    }
};

// Add new class computer

class Laptop : public Computer
{
public:
    Laptop(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };

    void showInfo() override
    {
        cout << "System config Laptop: " <<
                this->RAM << "; " <<
                this->HDD << "; " <<
                this->CPU << "; " << endl;
    };
private:
    string RAM;
    string HDD;
    string CPU;

    string getCPU() const
    {
        return CPU;
    }

    string getHDD() const
    {
        return HDD;
    }

    string getRAM() const
    {
        return RAM;
    }

};


// Base class for Abstract Factory

class AbstractComputerFactory
{
public:
    virtual Computer *createComputer() = 0;
};

class HomeComputerFactory : public AbstractComputerFactory
{
public:

    HomeComputerFactory(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };

    Computer *createComputer() override
    {
        return new HomePC(RAM, HDD, CPU);
    };

private:
    string RAM;
    string HDD;
    string CPU;
};

class ServerComputerFactory : public AbstractComputerFactory
{
public:

    ServerComputerFactory(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };

    Computer *createComputer() override
    {
        return new ServerPC(RAM, HDD, CPU);
    };

private:
    string RAM;
    string HDD;
    string CPU;
};

// add new factory class

class LaptopComputerFactory : public AbstractComputerFactory
{
public:

    LaptopComputerFactory(string ram, string hdd, string cpu) :
    RAM(ram), HDD(hdd), CPU(cpu)
    {
    };

    Computer *createComputer() override
    {
        return new Laptop(RAM, HDD, CPU);
    };

private:
    string RAM;
    string HDD;
    string CPU;
};

class ComputeFactory{
public:

    static Computer *getComputer(AbstractComputerFactory *factory)
    {
        return factory->createComputer();
    }
};


int main(int argc, char** argv)
{
    Computer *pc = ComputeFactory::getComputer(new HomeComputerFactory("16GB", "1TB", "2.2GH"));
    pc->showInfo();
    delete(pc);

    // Create new instance
    Computer *laptopPC = ComputeFactory::getComputer(new LaptopComputerFactory("32GB", "1TB", "2.6GH"));
    laptopPC->showInfo();
    delete (laptopPC);

    return 0;
}

