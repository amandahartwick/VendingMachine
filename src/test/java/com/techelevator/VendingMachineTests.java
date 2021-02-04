package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTests {
    @Test
    public void parseSlot_returns_slot(){

        VendingMachine v = new VendingMachine();
        Slot s = v.parseSlot("A1|YumYum|2.00|Candy");

        Double price = 2.00;
        Double itemPrice = s.getItem().getPrice();

        Assert.assertEquals("A1",s.getName());
        Assert.assertEquals("YumYum",s.getItem().getName());
        Assert.assertEquals(price,itemPrice);
        Assert.assertTrue(s.getItem() instanceof Candy);
    }

}
