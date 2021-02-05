package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    //FIELDS
    private ArrayList<Slot> slots;

    //METHODS
    //constructs a vending machine from a file
    public VendingMachine(File file) {
       slots = new ArrayList<Slot>();
        try(Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNextLine()) {
                Slot s = parseSlot(fileScan.nextLine());
                if(s!=null) {
                    slots.add(s);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    //default constructor, for testing purposes.
    public VendingMachine(){
        slots = new ArrayList<Slot>();
    }

    //another tester constructor
    public VendingMachine(Slot s){
        slots = new ArrayList<Slot>();
        slots.add(s);
    }

    //Prints Menu of all available items.
    public void printMenu(){
        for(Slot s: slots){
                System.out.println(s);
        }
    }

    //Returns item from selected slot, it slot exists.
    //Otherwise, if item exists but is sold out, it prints that and returns null.
    //if item does not exist, it prints that and returns null.
    public String buyItem(String slotName, Account account){
        for (Slot s: slots) {

           if(slotName.equals(s.getName())) {

               if (s.itemsSize() > 0 && s.getItem().getPrice() <= account.getBalance()) {

                   account.purchaseItem(s.dispense(),slotName);
                   return "Purchase successful!";
               } else if (s.itemsSize() == 0) {

                   System.out.println("Item is sold out. Sorry.");
                   return "Item is sold out. Sorry.";

               } else if (s.getItem().getPrice() > account.getBalance()) {

                   System.out.println("You did not add enough money. Please add more, then try again.");
                   return "You did not add enough money. Please add more, then try again.";
               }
           }

        }

        System.out.println("That item doesn't exist!");
        return "That item doesn't exist!";
    }

    //turns strings into slots full of food
    public Slot parseSlot(String input){
        try {
            String[] arr = input.split("\\|");

            String slotName = arr[0];
            String foodName = arr[1];
            double price = Double.parseDouble(arr[2]);
            Item item = null;


            if ("Chip".equals(arr[3])) {
                item = new Chips(foodName, price);
            } else if ("Drink".equals(arr[3])) {
                item = new Drink(foodName, price);
            } else if ("Gum".equals(arr[3])) {
                item = new Gum(foodName, price);
            } else if ("Candy".equals(arr[3])) {
                item = new Candy(foodName, price);
            }

            return new Slot(slotName, item);
        }catch(Exception e){
            System.out.println("There was a problem with your input file. Slot creation was not successful.");
        }
        return null;
    }

    public void purchaseMenu(Scanner scan){
        Account a = new Account();
        while(true){
            System.out.println("\n(1) Feed Money\n" +
                    "(2) Select Product\n" +
                    "(3) Finish Transaction\n" +
                    "\n" +
                    " Current Money Provided: \\"+a.getBalance());
            String input = scan.nextLine();
            if("1".equals(input)){
                System.out.println("Please enter your money:");
                input = scan.nextLine();
                int money = 0;
                try{
                    money = Integer.parseInt(input);
                }
                catch(NumberFormatException e){
                    System.out.println("That was bad money. Zero dollars added.");
                }
                a.feedMoney(money);
            }
            else if("2".equals(input)){
                System.out.println("Please enter the slot number for your item:\n");
                printMenu();
                input = scan.nextLine();
                buyItem(input, a);
            }
            else if("3".equals(input)){
                a.giveChange();
                break;
            }
        }
    }


}
