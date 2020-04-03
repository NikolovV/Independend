/*
 * Object pooling can offer a significant performance boost; it is most effective
 * in situations where the cost of initializing a class instance is high,
 * the rate of instantiation of a class is high, and the number of instantiations
 * in use at any one time is low.
 * Object Pools are usually implemented as Singletons.
 *
 */

#include <cstdlib>
#include <iostream>
#include <list>

using namespace std;

class DataBase
{
public:
    DataBase(int memoryUsage, int cpuUsage) :
    memoryUsage(memoryUsage), cpuUsage(cpuUsage)
    {
    }
    void clearCash()
    {
        this->memoryUsage = 0;
        this->cpuUsage = 0;
    }
    int getCpuUsage() const
    {
        return cpuUsage;
    }
    int getMemoryUsage() const
    {
        return memoryUsage;
    }
    void setCpuUsage(int cpuUsage)
    {
        this->cpuUsage = cpuUsage;
    }
    void setMemoryUsage(int memoryUsage)
    {
        this->memoryUsage = memoryUsage;
    }
private:
    int memoryUsage;
    int cpuUsage;
};

class ObjectPool
{
public:
    DataBase *getDBConnection()
    {
        if (db.empty())
        {
            cout << "Creating new db connection..." << endl;
            DataBase * con = new DataBase(128, 50);
            this->db.push_back(con);
            return con;
        }
        else
        {
            cout << "Reusing exiting connection..." << endl;
            DataBase *con = db.front();
            db.pop_front();
            return con;
        }
    }
    void retResource(DataBase *connection)
    {
        connection->clearCash();
        this->db.push_back(connection);
    }
    static ObjectPool* GetInstance()
    {
        if (instance == 0)
        {
            return new ObjectPool();
        }
        return instance;
    }
    void showStatistics(DataBase *con)
    {
        cout << "Connection in pool: " << db.size() << endl;
        cout << "Statistics: CPU usage: " << con->getCpuUsage() << " % "
                << "Memory usage: " << con->getMemoryUsage() << " MB" << endl;
    }

private:
    ObjectPool()
    {
    };
    list<DataBase *> db;
    static ObjectPool *instance;
};

ObjectPool *ObjectPool::instance = 0;

int main(int argc, char** argv)
{

    ObjectPool *objPool = ObjectPool::GetInstance();
    DataBase *peshoDB = objPool->getDBConnection();
    peshoDB->setCpuUsage(192);
    peshoDB->setCpuUsage(20);
    objPool->showStatistics(peshoDB);
    objPool->retResource(peshoDB);

    cout << endl;
    DataBase *goshoDB = objPool->getDBConnection();
    goshoDB->setCpuUsage(80);
    goshoDB->setMemoryUsage(100);
    objPool->showStatistics(goshoDB);

    cout << endl;
    DataBase *ginkaDB = objPool->getDBConnection();
    ginkaDB->setCpuUsage(15);
    ginkaDB->setMemoryUsage(64);
    objPool->showStatistics(ginkaDB);

    objPool->retResource(goshoDB);
    objPool->retResource(ginkaDB);

    return 0;
}

