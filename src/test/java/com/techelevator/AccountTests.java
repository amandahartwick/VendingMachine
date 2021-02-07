package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class AccountTests {
	@Test
	public void giveChange_returns_zero_balance() {
		 Account account = new Account();
		 account.feedMoney(1);
		 account.giveChange();
		 
		 Double expected = 0.0;
		 Double actual = account.getBalance();
		 
		 Assert.assertEquals(expected, actual);
		 // assertEquals is deprecated: https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html#assertArrayEquals(java.lang.String,%20double[],%20double[],%20double)
	}

	@Test
	public void feedMoney_updates_balance() {
	Account account = new Account();
	account.feedMoney(5);
	
	Double expected = 5.0;
	Double actual = account.getBalance();
	Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void purchaseItem_updates_balance() {
		VendingMachine v = new VendingMachine();
		Account account = new Account();
		
        Item item = new Candy("YumYum", 2.00);
        
		account.feedMoney(5);
		account.purchaseItem(item, "A1", v.getSalesReport());
		
		Double expected = 3.0;
		Double actual = account.getBalance();
		
		Assert.assertEquals(expected, actual);
	}

}
