package com.techelevator;

import java.io.File;

public class Main {
    public static void main(String[] args){

        File file = new File("vendingmachine.csv");
        VendingMachine v = new VendingMachine(file);
        v.printMenu();

        Account a = new Account();
        a.feedMoney(10);
        v.buyItem("B3", a);
        System.out.println(a.getBalance());

    }
}
