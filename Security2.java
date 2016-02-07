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

/**
 *
 * @author kevinripley
 */
public class Security2 {

    private static String hash_values;
    private static int hacked;
    private static double sec;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader file = new FileReader("/Users/kevinripley/Desktop/md5-hash.txt");
        try (BufferedReader br = new BufferedReader(file)) {
            convert();
//test println        
// System.out.println("Could not find values = " + dictionary);
            while ((hash_values = br.readLine()) != null) {
// System.out.println(hash_values);
//                if (dictionary.equals(hash_values)) {
//                    System.out.println(dictionary);
//                } else {
//                    System.out.println("Not your day!");
//                }
//System.out.println(printStatement(hash_values));
            }
//Test println
// System.out.println(printStatement(hash_values));
        }
    }

    public static String printStatement(String has) {
        String ths = "The password for hash value " + has + " is " + hacked + ",it takes the program " + sec + " sec to recover this password.";

        return ths;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
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
        String dictionary;
        while ((dictionary = dr.readLine()) != null) {
            String diction = getMD5(dictionary);
            //outputs the diction of the hash values of the dictionary to an empty txt file
            File outFile = new File("/Users/kevinripley/Desktop/converted.txt");
            FileWriter fWriter;
            fWriter = new FileWriter(outFile, true);
            try (PrintWriter pWriter = new PrintWriter(fWriter)) {
                pWriter.println(diction);
            }
        }
    }
}
