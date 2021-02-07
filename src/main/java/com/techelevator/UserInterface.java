package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
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
                SalesReport s = v.getSalesReport();
                s.print(s.getSalesReportName());
                System.exit(0);
            }
            else if("4".equals(input)){
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy(hh:mm:ss)");
                String date = ldt.format(formatter);
                String fileName = "SalesReport_"+date+".txt";
                File sales = new File(fileName);
                try {
                    sales.createNewFile();
                } catch (IOException e) {
                    System.out.println("lol it still doesn't work");
                }
                v.getSalesReport().print(sales);
            }
            else if("Umbrella".equalsIgnoreCase(input)){
                Art.print();
            }
        }

    }
}
