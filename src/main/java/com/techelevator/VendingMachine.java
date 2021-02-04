package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    //FIELDS
    private ArrayList<Slot> slots;

    //METHODS
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
    public Boolean buyItem(String slotName, Account account){
        for (Slot s: slots) {

           if(slotName.equals(s.getName())) {

               if (s.itemsSize() > 0 && s.getItem().getPrice() <= account.getBalance()) {

                   account.purchaseItem(s.dispense(),slotName);

                   return true;

               } else if (s.getItem().getPrice() > account.getBalance()) {

                   System.out.println("You did not add enough money. Please add more, then try again.");

                   return false;

               } else if (s.itemsSize() == 0) {

                   System.out.println("Item is sold out. Sorry.");

                   return false;

               }

           }

        }

        System.out.println("That item doesn't exist!");

        return false;
    }

    public Slot parseSlot(String input){

        String[] arr = input.split("\\|");

        String slotName = arr[0];
        String foodName = arr[1];
        double price = Double.parseDouble(arr[2]);
        Item item = null;

        switch (arr[3]){
            case "Chip":{
                item = new Chips(foodName,price);
            }
            case "Drink":{
                item = new Drink(foodName,price);
            }
            case "Gum":{
                item = new Gum(foodName,price);
            }
            case "Candy":{
                item = new Candy(foodName,price);
            }
        }

        return new Slot(slotName,item);
    }


}
