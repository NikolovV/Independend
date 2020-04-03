/*
 * Command Pattern is used to implement lose coupling in a request-response model.
 * In command pattern, the request is send to the invoker and invoker pass it
 * to the encapsulated command object.
 * Command object passes the request to the appropriate method of Receiver to perform the specific action.
 * Command establishes unidirectional connections between senders and receivers.
 */

#include <cstdlib>
#include <iostream>
#include <memory>

using namespace std;

// Interface for base functionality of servers ( response model )

class FileOperation
{
public:
    virtual string open(string commandText) = 0;
    virtual string write(string commandText) = 0;
    virtual string read(string commandText) = 0;
    virtual string close(string commandText) = 0;
    virtual string listFiles(string commandText) = 0;
};

// Current implementation of base functionality

class WindowsServer : public FileOperation
{
public:
    string open(string commandText) override
    {
        return "WindowsServer:\\> open " + commandText + " file on HDFS FS.";
    };
    string write(string commandText) override
    {
        return "WindowsServer:\\> write " + commandText + " file to HDFS FS.";
    };
    string read(string commandText) override
    {
        return "WindowsServer:\\> read " + commandText + " file from HDFS FS.";
    };
    string close(string commandText) override
    {
        return "WindowsServer:\\> close " + commandText + " file on HDFS FS.";
    };
    string listFiles(string commandText) override
    {
        return "WindowsServer:\\>dir " + commandText;
    };
};

class UnixServer : public FileOperation
{
public:
    string open(string commandText) override
    {
        return "UnixServer:\\> open file " + commandText + " on EXT4 FS.";
    };
    string write(string commandText) override
    {
        return "UnixServer:\\> write file " + commandText + " to EXT4 FS.";
    };
    string read(string commandText) override
    {
        return "UnixServer:\\> read file " + commandText + " from EXT4 FS.";
    };
    string close(string commandText) override
    {
        return "UnixServer:\\> close file " + commandText + " on EXT4 FS.";
    };
    string listFiles(string commandText) override
    {
        return "UnixServer:\\>ls " + commandText;
    };
};

// Interface for base command model (request model).

class Command
{
public:
    virtual string runCommand(string commandText) = 0;
};

// Current implementation of each specific command calling the appropriate method of "Receiver".

class OpenCommand : public Command
{
public:
    OpenCommand(shared_ptr<FileOperation> fileOperation) :
    fileOperation(fileOperation)
    {
    }
    string runCommand(string commandText) override
    {
        return fileOperation.get()->open(commandText);
    };

private:
    shared_ptr<FileOperation> fileOperation;
};

class WriteCommand : public Command
{
public:
    WriteCommand(shared_ptr<FileOperation> fileOperation) :
    fileOperation(fileOperation)
    {
    }
    string runCommand(string commandText) override
    {
        return fileOperation.get()->write(commandText);
    };

private:
    shared_ptr<FileOperation> fileOperation;
};

class ReadCommand : public Command
{
public:
    ReadCommand(shared_ptr<FileOperation> fileOperation) :
    fileOperation(fileOperation)
    {
    }
    string runCommand(string commandText) override
    {
        return fileOperation.get()->read(commandText);
    };

private:
    shared_ptr<FileOperation> fileOperation;
};

class CloseCommand : public Command
{
public:
    CloseCommand(shared_ptr<FileOperation> fileOperation) :
    fileOperation(fileOperation)
    {
    }
    string runCommand(string commandText) override
    {
        return fileOperation.get()->close(commandText);
    };

private:
    shared_ptr<FileOperation> fileOperation;
};

class ListCommand : public Command
{
public:
    ListCommand(shared_ptr<FileOperation> fileOperation) :
    fileOperation(fileOperation)
    {
    }
    string runCommand(string commandText) override
    {
        return fileOperation.get()->listFiles(commandText);
    };

private:
    shared_ptr<FileOperation> fileOperation;
};

// Invoker class passes the request to the command (requestor) object to process it.

class AdminConsoleShell
{
public:
    AdminConsoleShell(shared_ptr<Command> command) :
    command(command)
    {
    }
    void executeCommand(string commandText)
    {
        cout << command.get()->runCommand(commandText) << endl;
    }
    void setCommand(shared_ptr<Command> command)
    {
        this->command = command;
    }
private:
    shared_ptr<Command> command;
};
void unixServerTest()
{
    shared_ptr<FileOperation> unix = make_shared<UnixServer>();
    AdminConsoleShell peshoAdministrator = AdminConsoleShell(make_shared<ListCommand>(unix));
    peshoAdministrator.executeCommand("conf*");

    peshoAdministrator.setCommand(make_shared<OpenCommand>(unix));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<ReadCommand>(unix));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<WriteCommand>(unix));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<CloseCommand>(unix));
    peshoAdministrator.executeCommand("conf.xml");
}
void windowsServerTest()
{
    shared_ptr<FileOperation> windows = make_shared<WindowsServer>();
    AdminConsoleShell peshoAdministrator = AdminConsoleShell(make_shared<ListCommand>(windows));
    peshoAdministrator.executeCommand("conf*");

    peshoAdministrator.setCommand(make_shared<OpenCommand>(windows));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<ReadCommand>(windows));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<WriteCommand>(windows));
    peshoAdministrator.executeCommand("conf.xml");

    peshoAdministrator.setCommand(make_shared<CloseCommand>(windows));
    peshoAdministrator.executeCommand("conf.xml");
}
int main(int argc, char** argv)
{
    cout << "============== Test UNIX ===========" << endl;
    unixServerTest();
    cout << "============== End Test UNIX ===========" << endl;
    cout << endl;

    cout << "============== Test Windows ===========" << endl;
    windowsServerTest();
    cout << "============== End Test Windows ===========" << endl;

    return 0;
}