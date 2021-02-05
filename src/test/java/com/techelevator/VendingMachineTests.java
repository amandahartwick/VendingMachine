package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTests {
    @Test
    public void parseSlot_with_candy_returns_slot_of_candy(){

        VendingMachine v = new VendingMachine();
        Slot s = v.parseSlot("A1|YumYum|2.00|Candy");

        Double price = 2.00;
        Double itemPrice = s.getItem().getPrice();

        Assert.assertEquals("A1",s.getName());
        Assert.assertEquals("YumYum",s.getItem().getName());
        Assert.assertEquals(price,itemPrice);
        Assert.assertTrue(s.getItem() instanceof Candy);
    }

    @Test
    public void parseSlot_with_chip_returns_slot_of_chip(){

        VendingMachine v = new VendingMachine();
        Slot s = v.parseSlot("A1|YumYum|2.00|Chip");

        Double price = 2.00;
        Double itemPrice = s.getItem().getPrice();

        Assert.assertEquals("A1",s.getName());
        Assert.assertEquals("YumYum",s.getItem().getName());
        Assert.assertEquals(price,itemPrice);
        Assert.assertTrue(s.getItem() instanceof Chips);
    }

    @Test
    public void parseSlot_with_gum_returns_slot_of_gum(){

        VendingMachine v = new VendingMachine();
        Slot s = v.parseSlot("A1|YumYum|2.00|Gum");

        Double price = 2.00;
        Double itemPrice = s.getItem().getPrice();

        Assert.assertEquals("A1",s.getName());
        Assert.assertEquals("YumYum",s.getItem().getName());
        Assert.assertEquals(price,itemPrice);
        Assert.assertTrue(s.getItem() instanceof Gum);
    }

    @Test
    public void parseSlot_with_drink_returns_slot_of_drink(){

        VendingMachine v = new VendingMachine();
        Slot s = v.parseSlot("A1|YumYum|2.00|Drink");

        Double price = 2.00;
        Double itemPrice = s.getItem().getPrice();

        Assert.assertEquals("A1",s.getName());
        Assert.assertEquals("YumYum",s.getItem().getName());
        Assert.assertEquals(price,itemPrice);
        Assert.assertTrue(s.getItem() instanceof Drink);
    }

    @Test
    public void buyItem_when_slot_is_empty_returns_sold_out(){
        Slot s = new Slot("A1",new Candy("YumYum",2.00));
        VendingMachine v = new VendingMachine(s);
        Account a = new Account();
        a.feedMoney(3);
        s.dispense();
        s.dispense();
        s.dispense();
        s.dispense();
        s.dispense();
        String output = v.buyItem("A1",a);
        Assert.assertEquals("Item is sold out. Sorry.",output);
    }

    @Test
    public void buyItem_when_not_enough_money_returns_not_enough_money(){
        Slot s = new Slot("A1",new Candy("YumYum",2.00));
        VendingMachine v = new VendingMachine(s);
        Account a = new Account();
        a.feedMoney(1);
        String output = v.buyItem("A1",a);
        Assert.assertEquals("You did not add enough money. Please add more, then try again.",output);
    }

    @Test
    public void buyItem_when_all_good_is_successful(){
        Slot s = new Slot("A1",new Candy("YumYum",2.00));
        VendingMachine v = new VendingMachine(s);
        Account a = new Account();
        a.feedMoney(3);
        String output = v.buyItem("A1",a);
        Assert.assertEquals("Purchase successful!",output);
    }

    @Test
    public void buyItem_when_item_doesnt_exist_returns_as_such(){
        VendingMachine v = new VendingMachine();
        Account a = new Account();
        a.feedMoney(3);
        String output = v.buyItem("A2",a);
        Assert.assertEquals("That item doesn't exist!",output);
    }
}
