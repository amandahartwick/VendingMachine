package com.techelevator;

public class Account {
    //FIELDS
    private Double balance;

    //METHODS

    //adds money to balance.
    public void feedMoney(int money){
        balance+=money;
    }

    //subtracts price of item from balance.
    public void purchaseItem(double price){
        balance-=price;
    }

    //returns current balance.
    public double getBalance(){
        return balance;
    }
}
