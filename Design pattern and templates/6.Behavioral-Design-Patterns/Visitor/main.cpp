/*
 * The Visitor pattern represents an operation to be performed on the elements
 * of an object structure without changing the classes on which it operates.
 * With the help of visitor pattern, we can move the operational logic from the objects to another class.
 * The benefit of this pattern is that if the logic of operation changes,
 * then we need to make change only in the visitor implementation rather than doing it in all the item classes.
 * Another benefit is that adding a new item to the system is easy,
 * it will require change only in visitor interface and implementation and existing item classes will not be affected.
 */

#include <cstdlib>
#include <iostream>
#include <memory>
#include <vector>
#include <sys/unistd.h>

using namespace std;

// Abstract class / interface for type of Card

class Card
{
public:
    virtual string getType() = 0;
    virtual float getBalace() = 0;
    virtual void widraw(float payment) = 0;
};

// Current implementation for Card type

class CreditCard : public Card
{
public:
    CreditCard(string type, float balance) :
    cardType(type), balance(balance)
    {
    }
    float getBalace() override
    {
        return this->balance;
    }
    string getType() override
    {
        return this->cardType;
    }
    void widraw(float payment) override
    {
        this->balance -= payment;
    }

private:
    float balance;
    string cardType;
};

/*
 * The Component interface declares an "accept" method ( pay ) that should take the base
 * visitor interface as an argument.
 */
class Item
{
public:
    virtual float pay(const shared_ptr<class ShoppingCard> card) = 0;
    virtual string getType() const = 0;
};

/*
 * The Visitor Interface declares a set of visiting methods that correspond to
 * component classes. The signature of a visiting method allows the visitor to
 * identify the exact class of the component that it's dealing with.
 */
class ShoppingCard
{
public:
    virtual float calculateBill(class TV *tv) = 0;
    virtual float calculateBill(class GSM *gsm) = 0;
};

/*
 * Current component implementation
 * Each Concrete Component must implement the "Accept" method ( pay ) in such a way that
 * it calls the visitor's method corresponding to the component's class.
 */
class TV : public Item
{
public:
    TV(string type, float price) : type(type), price(price)
    {
    }
    float pay(const shared_ptr<ShoppingCard> card) override
    {
        return card.get()->calculateBill(this);
    };
    float getPrice() const
    {
        return price;
    }
    string getType() const
    {
        return type;
    }
private:
    string type;
    float price;
};

class GSM : public Item
{
public:
    GSM(string type, float price) :
    type(type), price(price)
    {
    }
    float pay(const shared_ptr<ShoppingCard> card) override
    {
        return card.get()->calculateBill(this);
    };
    float getPrice() const
    {
        return price;
    }
    string getType() const
    {
        return type;
    }
private:
    string type;
    float price;
};

/*
 * Concrete Visitors implement several versions of the same algorithm, which can
 * work with all concrete component classes.
 */
class CashPayment : public ShoppingCard
{
public:
    CashPayment(float cash) : receivedCash(cash)
    {
    }
    float calculateBill(TV* tv) override
    {
        if (tv->getPrice() > this->receivedCash)
        {
            cout << "Not enough money for " + tv->getType() + " !" << endl;
            return 0;
        }
        this->receivedCash -= tv->getPrice();
        return tv->getPrice();
    };
    float calculateBill(GSM* gsm) override
    {
        if (gsm->getPrice() > this->receivedCash)
        {
            cout << "Not enough money for " + gsm->getType() + " !" << endl;
            return 0;
        }

        this->receivedCash -= gsm->getPrice();
        return gsm->getPrice();
    };

private:
    float receivedCash;
};

// Another visitor

class CardPayment : public ShoppingCard
{
public:
    CardPayment(unique_ptr<Card> card) :
    card(move(card))
    {
    }
    float calculateBill(TV* tv) override
    {
        if (tv->getPrice() > card.get()->getBalace())
        {
            cout << "Not enough balance in " + card.get()->getType() +
                    " for " + tv->getType() + "!" << endl;
            return 0;
        }
        card.get()->widraw(tv->getPrice());
        return tv->getPrice();
    }
    float calculateBill(GSM* gsm) override
    {
        if (gsm->getPrice() > card.get()->getBalace())
        {
            cout << "Not enough balance in " + card.get()->getType() +
                    " for " + gsm->getType() + "!" << endl;
            return 0;
        }
        card.get()->widraw(gsm->getPrice());
        return gsm->getPrice();
    }
private:
    unique_ptr<Card> card;
};

/*
 * The client code can run visitor operations over any set of elements without
 * figuring out their concrete classes. The accept operation directs a call to
 * the appropriate operation in the visitor object.
 */
class Customer
{
public:
    Customer(vector<shared_ptr<Item> > items, vector<shared_ptr<ShoppingCard> > shoppingCard) :
    items(items), shoppingCard(shoppingCard)
    {
        this->total = 0.0;
    }
    void pay()
    {
        int shoppingCardIndex = 0;
        for (auto elem = items.begin(); elem != items.end();)
        {
            float result = elem.base()->get()->pay(shoppingCard[shoppingCardIndex]);
            if (result == 0)
            {
                cout << "Check for other payment method..." << endl;
                sleep(1);
                shoppingCardIndex++;
                if (shoppingCardIndex >= shoppingCard.size())
                {
                    cout << "Not enough money and no other payment. Return some staff!" << endl;
                    return;
                }
                else
                {
                    continue;
                }
            }

            cout << elem.base()->get()->getType() + " payed" << endl;
            this->total += result;
            items.erase(elem);
        }
        cout << "Items paid." << endl;
        printf("Total sum: %.2f\n", this->total);
    }
private:
    vector<shared_ptr<Item>> items;
    vector<shared_ptr<ShoppingCard>> shoppingCard;

    float total;
};
int main(int argc, char** argv)
{
    vector<shared_ptr < Item >> items({make_shared<TV>("TradeMark-TV", 699.99),
                                      make_shared<GSM>("Trademark-GSM", 889.79)});

    vector<shared_ptr < ShoppingCard >> shoppingCard({make_shared<CashPayment>(1500),
                                                     make_shared<CardPayment>(make_unique<CreditCard>("Credit Card", 1000))});

    Customer customer = Customer(items, shoppingCard);
    customer.pay();

    return 0;
}