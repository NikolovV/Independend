/*
 * Interpreter pattern provides a way to evaluate language grammar or expression.
 * This pattern involves implementing an expression interface which tells to
 * interpret a particular context. This pattern is used in SQL parsing, symbol processing engine etc.
 *
 */

#include <cstdlib>
#include <iostream>
#include <vector>
#include <string.h>
#include <memory>

using namespace std;

//Interpreter context engine that will do the interpretation work.

class Interpreter
{
public:
    Interpreter(vector<string> listBanWords) :
    listBanWords(listBanWords)
    {
    }
    string removeBanWords(string text)
    {
        string result = text;

        for (auto elem : this->listBanWords)
        {
            size_t startPosition = result.find(elem);
            if (startPosition != string::npos)
            {
                for (int i = startPosition; i < (startPosition + elem.length()); i++)
                {
                    result[i] = '*';
                }
            }
        }

        return result;
    }
    string hidePhoneNumber(string text)
    {
        string result = text;
        size_t startPostion = 0;

        while (startPostion != string::npos)
        {
            startPostion = result.find("359", startPostion);
            if (startPostion != string::npos)
            {
                int index = startPostion + 3;
                while (isdigit(result[index]) || isspace(result[index]) || ispunct(result[index]))
                {
                    if (isspace(result[index]) || ispunct(result[index]))
                    {
                        index++;
                    }
                    else
                    {
                        result[index++] = '*';
                    }
                    startPostion = index;
                }
            }
        }

        return result;
    }

private:
    vector<string> listBanWords;
};

// Interface of Expression that will consume the functionalities provided by the interpreter context.

class Expression
{
public:
    virtual string interpret(Interpreter interpreter) = 0;
};

// Current implementation of Expression.

class MaskBadWord : public Expression
{
public:
    MaskBadWord(string textToMask) :
    textToMask(textToMask)
    {
    }
    string interpret(Interpreter interpreter) override
    {
        return interpreter.removeBanWords(this->textToMask);
    };

private:
    string textToMask;
};

class MaskPhoneNumber : public Expression
{
public:
    MaskPhoneNumber(string textToMask) :
    textToMask(textToMask)
    {
    }
    string interpret(Interpreter interpreter) override
    {
        return interpreter.hidePhoneNumber(this->textToMask);
    };
private:
    string textToMask;
};
int main(int argc, char** argv)
{
    Interpreter interpreter = Interpreter({"stupid", "fool", "loser", "idiot"});

    unique_ptr<Expression> expr = make_unique<MaskBadWord>("He is so stupid and he is a total idiot. Only loser like him can do that.");
    cout << expr.get()->interpret(interpreter) << endl;

    MaskPhoneNumber exprPhoneNumner =
            MaskPhoneNumber("Company ABC Mob: +359123456789, mail: abc@gmail.com, location: str. \"ABC N 123, floor 123, wing B\"");
    cout << exprPhoneNumner.interpret(interpreter) << endl;

    return 0;
}