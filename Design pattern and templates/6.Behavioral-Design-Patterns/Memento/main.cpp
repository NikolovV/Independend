/*
 * The memento design pattern is used when we want to save the state of an
 * object so that we can restore later on.
 * Memento pattern is implemented with two objects – Originator and Caretaker.
 * The originator is the object whose state needs to be saved and restored and
 * it uses an inner class or interface to save the state of Object. The inner class is
 * called Memento and it’s private so that it can’t be accessed from other objects.
 * Using interface you may add some metadata operations to the interface,
 * but nothing that exposes the originator’s state.
 * Use the pattern when direct access to the object’s fields/getters/setters violates its encapsulation.
 */

#include <cstdlib>
#include <iostream>
#include <memory>
#include <vector>
#include <ctime>

using namespace std;

/*
 * The Memento interface / abstract class provides a way to retrieve the memento's metadata.
 * However, it doesn't expose the Originator's state.
 */
class Memento
{
public:
    virtual string getState() const = 0;
    virtual string getConnection() = 0;
    virtual string getSqlStatement() = 0;
};

/*
 * The Concrete Memento contains the infrastructure for storing the Originator's
 * state.
 */
class SqlExecutorMemento : public Memento
{
public:
    SqlExecutorMemento(string connection, string sqlStatement) :
    connection(connection), sqlStatement(sqlStatement)
    {
        std::time_t sysdate = std::time(0);
        this->date = ctime(&sysdate);
    }
    string getState() const override
    {
        string result = this->date + " - " + this->connection + " / \n" + this->sqlStatement;
        return result;
    }
    string getConnection() override
    {
        return this->connection;
    };
    string getSqlStatement() override
    {
        return this->sqlStatement;
    };

private:
    string date;
    string connection;
    string sqlStatement;
};

/*
 * The Originator holds some important state that may change over time. It also
 * defines a method for saving the state inside a memento and another method for
 * restoring the state from it.
 */
class SqlExecutor
{
public:
    SqlExecutor(string connection, string sqlStatement) :
    connection(connection), sqlStatement(sqlStatement)
    {
    }
    string executeStatament()
    {
        return "Connecting to - " + this->connection + " ...\n executing...\n" +
                this->sqlStatement;
    }
    shared_ptr<Memento> saveState()
    {
        return make_shared<SqlExecutorMemento>(this->connection, this->sqlStatement);
    };
    void undoState(shared_ptr<Memento> memento)
    {
        cout << "Restoring to: " + memento.get()->getState() << endl;
        this->connection = memento.get()->getConnection();
        this->sqlStatement = memento.get()->getSqlStatement();
    }
    void setConnection(string connection)
    {
        this->connection = connection;
    }
    void setSqlStatement(string sqlStatement)
    {
        this->sqlStatement = sqlStatement;
    }

private:
    string connection;
    string sqlStatement;
};

/*
 * The Caretaker doesn't depend on the Concrete Memento class. Therefore, it
 * doesn't have access to the originator's state, stored inside the memento. It
 * works with all mementos via the base Memento interface.
 */
class StatementHistory
{
public:
    StatementHistory(SqlExecutor* executor) :
    executor(executor)
    {
    }
    void saveState()
    {
        cout << "Saving state..." << endl;
        this->history.push_back(this->executor->saveState());
    }
    void undoState(int position)
    {
        if (!history.size())
        {
            cout << "No previous state." << endl;
            return;
        }
        if (position < 0 || position > history.size() - 1)
        {
            cout << "No such element: " + to_string(position) << endl;
            return;
        }

        cout << "Restoring history position: " + to_string(position) << endl;
        this->executor->undoState(this->history.at(position));
    }
    void showHistory()
    {
        cout << "History record from Memento ..." << endl;
        cout << "===============================" << endl;
        int index = 0;
        for (auto elem : history)
        {
            cout << to_string(index++) + ". " + elem.get()->getState() << endl;
            if (index <= history.size() - 1)
            {
                cout << "-------------------------------" << endl;
            }
        }
        cout << "===============================" << endl;
    }
private:
    SqlExecutor *executor;
    vector<shared_ptr<Memento>> history;
};
void test()
{
    int userId = 123;
    string stm = "select\n"
            "       first_name\n"
            "       last_name\n"
            "       address\n"
            "  from users\n"
            " where user_id = " + to_string(userId) +
            ";";
    SqlExecutor sqlExecutor = SqlExecutor("oracle", stm);
    StatementHistory stmhist = StatementHistory(&sqlExecutor);

    cout << sqlExecutor.executeStatament() << endl;
    stmhist.saveState();

    userId += 5;
    stm = "select\n"
            "       address\n"
            "       street_no\n"
            "       city\n"
            "  from address\n"
            " where user_id = " + to_string(userId) +
            ";";
    sqlExecutor.setSqlStatement(stm);
    cout << sqlExecutor.executeStatament() << endl;
    stmhist.saveState();

    stmhist.showHistory();

    stmhist.undoState(1);
}
int main(int argc, char** argv)
{
    test();
    return 0;
}