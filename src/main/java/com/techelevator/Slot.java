package com.techelevator;

import java.util.ArrayList;

public class Slot {

    //FIELDS
    private String name;
    private ArrayList<buyable> items;

    //METHODS
    //Constructor fills slot to capacity (5) when it is initially created.
    public Slot(String name, buyable item) {

        this.name = name;

        items = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            items.add(item);
        }
    }

    //Returns the amount of items left in the slot.
    public int itemsSize(){
        return items.size();
    }

    //Returns null if there are no more left (Sold out!).
    //Otherwise, it removes the item from the slot and returns it.
    public buyable dispense(){
       if(!items.isEmpty()) {
           buyable output = items.get(0);
           items.remove(0);
           return output;
       }
       return null;
    }

    //Returns the name of the slot.
    public String getName(){
        return name;
    }
}
