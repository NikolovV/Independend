/* 
 * Flyweight pattern is primarily used to reduce the number of objects created
 * and to decrease memory footprint and increase performance.
 * Flyweight pattern tries to reuse already existing similar kind objects
 * by storing them and creates new object when no matching object is found.
 * Factory is used to can cache and reuse existing class instances.
 */

#include <cstdlib>
#include <iostream>
#include <unordered_map>
#include <memory>
#include <list>

using namespace std;

// Base classs of shared (constant, intrinsic state) properties
class Icon
{
public:
    Icon()
    {
    }
    Icon(string name, int vPixel, int hPixel) :
    name(name), vPixel(vPixel), hPixel(hPixel)
    {
    }
    Icon(const Icon& other) :
    name(other.name), vPixel(other.vPixel), hPixel(other.hPixel)
    {
    }
    int getHPixel() const
    {
        return hPixel;
    }
    string getName() const
    {
        return name;
    }
    int getVPixel() const
    {
        return vPixel;
    }
    void print()
    {
        cout << "\tIcon name: " + this->getName() + "\n"
                "\tIcon size: " + to_string(this->getHPixel()) + "px x " +
                to_string(this->getVPixel()) + "px." << endl;
    }
private:
    string name;
    int vPixel;
    int hPixel;
};

// Base abstract class unique (changeable, extrinsic state) properties.
class File
{
public:
    File(string name, int sizeMB, Icon icon) :
    name(name), sizeMB(sizeMB), icon(icon)
    {
    }
    virtual void display()
    {
        cout << "\tFile name: " + this->name + "\n" +
                "\tFile size: " + to_string(this->sizeMB) + " MB\n";
        this->icon.print();
    };
private:
    string name;
    int sizeMB;
    Icon icon;
};

// Current implementation of extrinsic state object.
class MusicFile : public File
{
public:
    MusicFile(string name, int sizeMB, Icon icon) :
    File(name, sizeMB, icon)
    {
    }
    void display() override
    {
        cout << "File is playable." << endl;
        File::display();
    };
};

// Current implementation of extrinsic state object.
class Document : public File
{
public:
    Document(string name, int sizeMB, Icon icon) :
    File(name, sizeMB, icon)
    {
    }
    void display() override
    {
        cout << "Text document." << endl;
        File::display();
    };
};

/*
 * Flyweight factory class with pool of shared (intrinsic state) object.
 * Check for existing object in pool and return it.
 * If not exists create new and return it.
 */
class FlyweightFactory
{
public:
    Icon getIcon(Icon icon)
    {
        if (iconMap.find(icon.getName()) == iconMap.end())
        {
            iconMap.insert(make_pair(icon.getName().c_str(), icon));
        }

        return iconMap[icon.getName()];
    }
    unordered_map<string, Icon> getIconMap() const
    {
        return iconMap;
    }
private:
    unordered_map<string, Icon> iconMap;
};


int main(int argc, char** argv)
{
    FlyweightFactory ff;
    list<shared_ptr < File>> f;

    f.push_back(make_shared<MusicFile>(MusicFile("music - file 1", 3, ff.getIcon(Icon("defaultIcon", 150, 150)))));
    f.push_back(make_shared<MusicFile>(MusicFile("music - file 2", 3, ff.getIcon(Icon("albumCoverIcon", 155, 155)))));
    
    f.push_back(make_shared<Document>(Document("document 1", 3, ff.getIcon(Icon("defaultIcon", 150, 150)))));
    f.push_back(make_shared<Document>(Document("document 2", 3, ff.getIcon(Icon("wordDocumentIcon", 120, 150)))));
    
    for (auto elem : f)
    {
        elem.get()->display();
    }

    printf("\nDisplay shared object in pool: (%d unit):\n", ff.getIconMap().size());
    for (auto icon : ff.getIconMap())
    {
        cout << icon.first + ":\n";
        icon.second.print();
    }

    return 0;
}