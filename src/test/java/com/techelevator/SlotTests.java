package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class SlotTests {

    @Test
    public void constructor_creates_five_of_item() {
        buyable item = new Candy();
        Slot slot = new Slot("A1",item);

        Assert.assertEquals(5, slot.itemsSize());
    }

    @Test
    public void dispense_removes_item_and_returns_it() {
        buyable item = new Candy();
        Slot slot = new Slot("A1",item);
        buyable anotherItem = slot.dispense();

        Assert.assertEquals(4,slot.itemsSize());
        Assert.assertEquals(item, anotherItem);
    }

    @Test
    public void if_slot_is_empty_dispense_returns_null(){
        buyable item = new Candy();
        Slot slot = new Slot("A1",item);
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        buyable anotherItem = slot.dispense();

        Assert.assertEquals(0,slot.itemsSize());
        Assert.assertEquals(null, anotherItem);
    }
}
