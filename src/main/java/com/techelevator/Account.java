package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    //FIELDS
    private Double balance;
    private final DateTimeFormatter formatter;
    private final File file;

    //METHODS
    //constructor
    public Account(){
        this.balance = 0.0;
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        this.file = new File("Log.txt");
    }

    //adds money to balance, and calls helper method to print to log
    public void feedMoney(int money){
        double balanceBefore = balance;
        balance+=money;
        print("FEED MONEY",balanceBefore);
    }

    //subtracts price of item from balance, and calls helper method to print to log
    //additionally, prints transaction to console
    public void purchaseItem(Item item, String slotName){
        double balanceBefore = balance;
        balance-=item.getPrice();
        String transaction = item.getName() + " " + slotName;
        print(transaction, balanceBefore);
        System.out.println(item.getName()+" "+item.getPrice()+" "+balance+"\n"+item.getMessage());
    }

    //returns current balance.
    public double getBalance(){
        return balance;
    }

    //helper method to print transaction to the log
    public void print(String transaction, double balanceBefore){
        LocalDateTime ldt = LocalDateTime.now();
        String dateTime = ldt.format(formatter);
        try ( PrintWriter pw = new PrintWriter(new FileWriter(file,true))){

            pw.println(">"+dateTime+" "+transaction+" \\"+balanceBefore+" \\"+balance);

        } catch (IOException e) {
            System.out.println("There was a problem.");
        }
    }
}
