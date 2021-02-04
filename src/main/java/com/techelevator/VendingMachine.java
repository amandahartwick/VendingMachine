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
                slots.add(parseSlot(fileScan.nextLine()));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    //default constructor, for testing purposes.
    public VendingMachine(){

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
    public void buyItem(String slotName, Account account){
        for (Slot s: slots) {

           if(slotName.equals(s.getName())) {

               if (s.itemsSize() > 0 && s.getItem().getPrice() <= account.getBalance()) {

                   account.purchaseItem(s.dispense(),slotName);
                   return;

               } else if (s.getItem().getPrice() > account.getBalance()) {

                   System.out.println("You did not add enough money. Please add more, then try again.");

               } else if (s.itemsSize() == 0) {

                   System.out.println("Item is sold out. Sorry.");

               }

           }

        }

        System.out.println("That item doesn't exist!");
    }

    //turns strings into slots full of food
    public Slot parseSlot(String input){

        String[] arr = input.split("\\|");

        String slotName = arr[0];
        String foodName = arr[1];
        double price = Double.parseDouble(arr[2]);
        Item item = null;


            if("Chip".equals(arr[3])){
                item = new Chips(foodName,price);
            }
            else if("Drink".equals(arr[3])){
                item = new Drink(foodName,price);
            }
            else if("Gum".equals(arr[3])){
                item = new Gum(foodName,price);
            }
            else if("Candy".equals(arr[3])){
                item = new Candy(foodName,price);
            }

        return new Slot(slotName,item);
    }


}
