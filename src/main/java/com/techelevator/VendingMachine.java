package com.techelevator;

import java.util.ArrayList;

public class VendingMachine {
    //FIELDS
    private ArrayList<Slot> slots;

    //METHODS
    //This is temporary. Will be replaced by constructor that parses from file.
    public VendingMachine(ArrayList<Slot> slots) {
        this.slots = slots;
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
    public Item buyItem(String slotName, Account account){
        for (Slot s: slots) {

           if(slotName.equals(s.getName())) {

               if (s.itemsSize() > 0 && s.getItem().getPrice() <= account.getBalance()) {

                   account.purchaseItem(s.getItem().getPrice());

                   return s.dispense();

               } else if (s.getItem().getPrice() > account.getBalance()) {

                   System.out.println("You did not add enough money. Please add more, then try again.");

                   return null;

               } else if (s.itemsSize() == 0) {

                   System.out.println("Item is sold out. Sorry.");

                   return null;

               }

           }

        }

        System.out.println("That item doesn't exist!");

        return null;
    }


}
