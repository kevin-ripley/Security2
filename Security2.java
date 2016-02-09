/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;

/**
 *
 * @author kevinripley
 */
public class Security2 {

    private static String hash_value1 = "6f047ccaa1ed3e8e05cde1c7ebc7d958"; //found
    private static String hash_value2 = "275a5602cd91a468a0e10c226a03a39c";//found
    private static String hash_value3 = "b4ba93170358df216e8648734ac2d539";//found
    private static String hash_value4 = "dc1c6ca00763a1821c5af993e0b6f60a";//found
    private static String hash_value5 = "8cd9f1b962128bd3d3ede2f5f101f4fc";//found
    private static String hash_value6 = "554532464e066aba23aee72b95f18ba2";//found
    private static String new_hash;
    private static String hacked, old_school;
    private static double sec;
    private static String dictionary;
    private static int count;
    private static long end1;
    private static long end2;
    private static long end3;
    private static long end4;
    private static long end5;
    private static long end6;
    private static long elapsed1;
    private static long elapsed2;
    private static long elapsed3;
    private static long newtime1;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //FileReader file = new FileReader("/Users/kevinripley/Desktop/md5-hash.txt");
        FileReader hashed_file = new FileReader("/Users/kevinripley/Desktop/output.txt");
        FileReader dictionary = new FileReader("/Users/kevinripley/Desktop/realhuman_phill.txt");
        long start = System.nanoTime();
        try(BufferedReader dc = new BufferedReader(dictionary)){   
        try(BufferedReader hf = new BufferedReader(hashed_file)){
            // convert();
//test println   
            
            //System.out.println("Could not find values = " + dictionary);
            while((old_school = dc.readLine()) != null){
                while((new_hash = hf.readLine()) != null){
                    
                //use count and access old_school to get original password
                count++;
                //System.out.println(hash_values);
                if ((new_hash.equals(hash_value1))) {
                    
                    hacked = new_hash;
                    System.out.println(count);
                    end1 = System.nanoTime();
                    elapsed1 = end1 -start;
                    double seconds1 =((double) elapsed1) /1000000000;
                    System.out.println(printStatement(new_hash, hacked, count, seconds1));
                } else if((new_hash.equals(hash_value2))){
                    String two = new_hash;
                    System.out.println(count);
                    end2 = System.nanoTime();
                    
                    elapsed2 = end2 - start;
                    double seconds = ((double) elapsed2) / 1000000000;
                    System.out.println(printStatement(new_hash, two, count, seconds));
                    
                }else if((new_hash.equals(hash_value3))){
                    String three = new_hash;
                    System.out.println(count);
                    end3 = System.nanoTime();
                    elapsed3 = end3 - start;
                    System.out.println(printStatement(new_hash, three, count,elapsed3));
                    
                }else if((new_hash.equals(hash_value4))){
                    String four = new_hash;
                    System.out.println(count);
                    System.out.println(printStatement(new_hash, four, count, newtime1));
                    end4 = System.nanoTime();
                }else if((new_hash.equals(hash_value5))){
                    String five = new_hash;
                    System.out.println(count);
                    System.out.println(printStatement(new_hash, five, count, newtime1));
                    end5 = System.nanoTime();
                }else if((new_hash.equals(hash_value6))){
                    String six = new_hash;
                    System.out.println(count);
                    System.out.println(printStatement(new_hash, six, count, newtime1));
                    end6 = System.nanoTime();
                }
                 
                 
               
               
            }
                //System.out.println("Hash value not found!");
                count = 0;

            //System.out.println(printStatement(hash_values));
        
            }
            }
       // System.out.println("Done!?");
        }
        }

    public static String printStatement(String has, String hacked, int count, double newtime) {
        String ths = "The password for hash value " + has + " is " + hacked + ",it takes the program " + newtime + " sec to recover this password.";

        return ths;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //This method creates a new txt file with the hash values
    public static void convert() throws IOException {
        FileReader dict = new FileReader("/Users/kevinripley/Desktop/realhuman_phill.txt");
        BufferedReader dr = new BufferedReader(dict);

        while ((dictionary = dr.readLine()) != null) {
            String diction = getMD5(dictionary);
            //outputs the dictionary hash values of the dictionary to an empty txt file
            File outFile = new File("/Users/kevinripley/Desktop/output.txt");
            FileWriter fWriter;
            fWriter = new FileWriter(outFile, true);
            try (PrintWriter pWriter = new PrintWriter(fWriter)) {
                pWriter.println(diction);
            }
        }
    }
}
