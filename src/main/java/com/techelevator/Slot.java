package com.techelevator;

import java.util.ArrayList;

public class Slot {

    //FIELDS
    private String name;
    private ArrayList<Item> items;

    //METHODS
    //Constructor fills slot to capacity (5) when it is initially created.
    public Slot(String name, Item item) {

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
    public Item dispense(){
       if(!items.isEmpty()) {
           Item output = items.get(0);
           items.remove(0);
           return output;
       }
       return null;
    }

    //returns item for informational purposes
    public Item getItem(){
       if(!items.isEmpty()) {
           return items.get(0);
       }
       return null;
    }

    //Returns the name of the slot.
    public String getName(){
        return name;
    }

    //Returns string representation of item
    @Override
    public String toString(){
        String output = "\n"+name+" ";
        if(items.isEmpty()){
            output += "SOLD OUT";
        }
        else{
            output += items.get(0).getName()+" "+items.get(0).getPrice()+"\n* Quantity left: "+items.size();
            //A1 YumYum 2.50
            //Amount left: 5
        }
        return output;
    }
}
