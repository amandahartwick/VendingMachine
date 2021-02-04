package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class SlotTests {

    @Test
    public void constructor_creates_five_of_item() {
        Item item = new Candy("YumYum", 2.00);
        Slot slot = new Slot("A1",item);

        Assert.assertEquals(5, slot.itemsSize());
    }

    @Test
    public void dispense_removes_item_and_returns_it() {
        Item item = new Candy("YumYum", 2.00);
        Slot slot = new Slot("A1",item);
        Item anotherItem = slot.dispense();

        Assert.assertEquals(4,slot.itemsSize());
        Assert.assertEquals(item, anotherItem);
    }

    @Test
    public void if_slot_is_empty_dispense_returns_null(){
        Item item = new Candy("YumYum", 2.00);
        Slot slot = new Slot("A1",item);
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        slot.dispense();
        Item anotherItem = slot.dispense();

        Assert.assertEquals(0,slot.itemsSize());
        Assert.assertNull(anotherItem);
    }
}
