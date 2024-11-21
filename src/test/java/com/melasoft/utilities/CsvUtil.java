package com.melasoft.utilities;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CsvUtil {
/*
    public static void createCsvFile(Map<String, String> pricingDetails) {


        LocalDate startDate = LocalDate.now();
        String path = Paths.get(System.getProperty("user.dir"), "PriceDetails.csv").toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) { // Set append to true
        writer.write(" ------------" + startDate + "--------------");
        writer.newLine();
            if (new java.io.File(path).length() == 0) {
                writer.write("Product Type,Price");
                writer.newLine();
            }

            for (Map.Entry<String, String> entry : pricingDetails.entrySet()) {
                String productType = entry.getKey();
                String[] array = entry.getValue().split("\n");
                String price = array[0];

                writer.write(String.join(",", productType, price));
                writer.newLine();
            }

            writer.write("============================================================");
            writer.newLine();

            System.out.println("Data appended to CSV file at: " + path);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }



    }

*/


    public static void createCsvFile(Map<String, String> pricingDetails) {
        LocalDate startDate = LocalDate.now();
        String path = Paths.get(System.getProperty("user.dir"), "target/PriceDetails.csv").toString();
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            // Set append to true

            writer.write(" ------------" + startDate + "--------------");
            writer.newLine();


            if (new java.io.File(path).length() == 0) {
                writer.write("Product Type,Price,URL");
                writer.newLine();
            }
            String url = pricingDetails.get("url");

            for (Map.Entry<String, String> entry : pricingDetails.entrySet()) {
                String productType = entry.getKey();
                if ("url".equals(productType)) {
                    continue;
                }

                String[] array = entry.getValue().split("\n");
                String price = array[0];


                price = price.replace(",", ".");


                writer.write(String.join(",", productType, price, url));
                writer.newLine();
            }


            writer.write("============================================================");
            writer.newLine();

            System.out.println("Data appended to CSV file at: " + path);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }





}






