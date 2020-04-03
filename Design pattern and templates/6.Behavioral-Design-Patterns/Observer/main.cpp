/*
 * Observer design pattern is useful when you are interested in the state of an object
 * and want to get notified whenever there is any change. In observer pattern,
 * the object that watch on the state of another object are called Observer
 * and the object that is being watched is called Subject.
 * Subject contains a list of observers to notify of any change in it’s state,
 * so it should provide methods using which observers can register and unregister themselves.
 * Model-View-Controller (MVC) frameworks also use Observer pattern where
 * Model is the Subject and Views are observers that can register to get notified of any change to the model.
 */

#include <cstdlib>
#include <iostream>
#include <vector>
#include <memory>
#include <sys/unistd.h>

using namespace std;

/*
 * Abstract class / interface for Observer that will be notified when change happen.
 * And watch the state of Subject.
 * The Observer interface declares the alarm method, used by subjects.
 */
class AlarmComponent
{
public:
    virtual string alarm() = 0;
    virtual string getType() = 0;
protected:
    // Forward declaration
    class AlarmSystem *alarmSystem;
};

/*
 * The Subject owns some important state and notifies observers when the state
 * changes.
 */
class AlarmSystem
{
public:
    AlarmSystem()
    {
        this->isMotionDetect = false;
    }
    void attach(AlarmComponent *component)
    {
        cout << "Attaching... " + component->getType() << endl;
        alarmComponent.push_back(component);
    };
    void setAlarm()
    {
        if (!alarmComponent.size())
        {
            cout << "No alarm component attached!" << endl;
            return;
        }

        cout << "Attention! Intruder detected! Activating alarms..." << endl;
        isMotionDetect = true;
        alarm();
    }
    bool getIsMotionDetect() const
    {
        return isMotionDetect;
    }

private:
    void alarm()
    {
        for (auto component : alarmComponent)
        {
            cout << component->alarm() << endl;
        }
    }
    bool isMotionDetect;
    vector<AlarmComponent *> alarmComponent;
};

/*
 * Concrete Observers react to the updates issued by the Subject they had been attached to.
 */
class LigthAlarm : public AlarmComponent
{
public:
    LigthAlarm(AlarmSystem *system, string systemType)
    {
        this->alarmSystem = system;
        isMotionDetect = false;
        this->systemType = systemType;
        this->alarmSystem->attach(this);
    }
    string alarm() override
    {
        this->isMotionDetect = alarmSystem->getIsMotionDetect();
        if (this->isMotionDetect)
        {
            return "!**********! Lights ON. !**********!";
        }
        return "";
    }
    string getType() override
    {
        return this->systemType;
    };

private:
    bool isMotionDetect;
    string systemType;
};

class SoundAlarm : public AlarmComponent
{
public:
    SoundAlarm(AlarmSystem *system, string systemType)
    {
        this->alarmSystem = system;
        isMotionDetect = false;
        this->systemType = systemType;
        this->alarmSystem->attach(this);
    }
    string alarm() override
    {
        this->isMotionDetect = alarmSystem->getIsMotionDetect();
        if (this->isMotionDetect)
        {
            return "!**********! Alarm Sound ON. !**********!";
        }
        return "";
    }
    string getType() override
    {
        return this->systemType;
    };

private:
    bool isMotionDetect;
    string systemType;
};

AlarmSystem alarmSystem = AlarmSystem();
void activateAlarm(int signum)
{
    cout << "Motion detect!" << endl;
    cout << "Entr password: " << endl;
    string password;
    cin >> password;

    if (password.compare("12345") == 0)
    {
        cout << "Alarm disarmed." << endl;
    }
    else
    {
        cout << "Alarm activating!!!" << endl;
        alarmSystem.setAlarm();
    }

    exit(0);
}
int main(int argc, char** argv)
{
    alarmSystem.setAlarm();

    shared_ptr<AlarmComponent> lightAlarm = make_shared<LigthAlarm>(&alarmSystem, "LAC (Light alarm component)");
    shared_ptr<AlarmComponent> soundAlarm = make_shared<SoundAlarm>(&alarmSystem, "SAC (Sound alarm component)");

    signal(SIGINT, activateAlarm);

    while (true)
    {
        cout << "Checking for motion.(Ctrl+c to simulate open door/window. )" << endl;
        sleep(1);
    }

    return 0;
}