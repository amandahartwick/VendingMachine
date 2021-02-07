package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {
    private double allTimeSales;
    private Map<String, Integer> salesReport;

    public SalesReport(){
        salesReport = new HashMap<>();
        File salesReportFile = getSalesReportName();

        try(Scanner fileScan = new Scanner(salesReportFile)) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                if(line.equals("")){
                    String num = fileScan.nextLine();
                    allTimeSales = Double.parseDouble(num);
                }
                else{
                    parseSalesReport(line);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public void parseSalesReport(String input) {
        String[] arr = input.split("\\|"); //  take string that was put into the method and split it at | into array
        String key = arr[0];
        String num = arr[1]; //declaring key and num

        int value = Integer.parseInt(num); // parsing it into an integer

        salesReport.put(key,value); // put integer into a map
    }

    public File getSalesReportName() {
        File salesReportName = new File("SalesReportName.txt");
        String fileName = "";
        try(Scanner fileScan = new Scanner(salesReportName)) {
            fileName = fileScan.nextLine();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return new File (fileName);
    }

    public void setSalesReportName(File file) {
        File salesReportName = new File("SalesReportName.txt");
        try(PrintWriter pw = new PrintWriter(salesReportName)) {
            pw.println(file.getName());
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void updateSalesReport(String itemName, double itemPrice){
        allTimeSales+=itemPrice;
        boolean isRecorded = false;
        for(String s: salesReport.keySet()){
            if (s.equals(itemName)){
                salesReport.put(itemName,salesReport.get(itemName)+1);
                isRecorded = true;
            }
        }
        if(!isRecorded){
            salesReport.put(itemName,1);
        }
    }

    public void print(File file){
        setSalesReportName(file);
        try(PrintWriter pw = new PrintWriter(file)){
            pw.print(toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (String s : salesReport.keySet()) {
            output += s + "|" + salesReport.get(s) + "\n";
        }
        output += "\n" + allTimeSales;
        return output;
    }

}
