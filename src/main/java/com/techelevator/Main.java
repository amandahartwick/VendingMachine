package com.techelevator;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        File file = new File("vendingmachine.csv");
        VendingMachine v = new VendingMachine(file);
        Scanner scan = new Scanner(System.in);

        while(true){

            System.out.println("(1) Display Vending Machine Items" +
                    "\n(2) Purchase" +
                    "\n(3) Exit");

            String input = scan.nextLine();
            if("1".equals(input)){
                v.printMenu();
            }
            else if("2".equals(input)){
                v.purchaseMenu(scan);
            }
            else if("3".equals(input)){
                System.exit(0);
            }
        }

    }
}
