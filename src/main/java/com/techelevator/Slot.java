package com.techelevator;

import java.util.ArrayList;

public class Slot {
    private String name;
    private ArrayList<buyable> items;

    public Slot(String name, buyable item) {
        this.name = name;
        items = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            items.add(item);
        }
    }

    public int itemsSize(){
        return items.size();
    }

    public buyable dispense(){
       if(!items.isEmpty()) {
           buyable output = items.get(0);
           items.remove(0);
           return output;
       }
       return null;
    }

    public String getName(){
        return name;
    }
}
