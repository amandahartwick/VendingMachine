package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    //FIELDS
    private Double balance;
    private final DateTimeFormatter formatter;
    private final File file;
    
    //constructor
    public Account(){
        this.balance = 0.0;
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        this.file = new File("Log.txt");
    }

    //METHODS
     	
    //adds money to balance, and calls helper method to print to log
    public void feedMoney(int money){
        double balanceBefore = balance;
        balance+=money;
        print("FEED MONEY",balanceBefore);
    }

    //subtracts price of item from balance, and calls helper method to print to log
    //additionally, prints transaction to console
    public void purchaseItem(Item item, String slotName, SalesReport s){
        double balanceBefore = balance;
        balance-=item.getPrice();
        String transaction = item.getName() + " " + slotName;
        print(transaction, balanceBefore);
        System.out.println("Item: "+item.getName()+"\nPrice: "+item.getPrice()+"\nChange: "+balance+"\n"+item.getMessage());
        s.updateSalesReport(item.getName(), item.getPrice());
    }
    
    // Give Change
    public void giveChange() {

        double balanceBefore = balance;
        int change = (int)(Math.ceil(balance * 100));
        
        int quarters = Math.round((int)change / 25);
        change = change % 25;
        int dimes = Math.round((int)change / 10);
        change = change % 10;
        int nickels = Math.round((int)change / 5);
        change = change % 5;
        int pennies = Math.round((int)change / 1);
        
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        
        balance = 0.0;

        print("GIVE CHANGE", balanceBefore);
        
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
