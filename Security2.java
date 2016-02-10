package security2;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Kevin Ripley and Genevieve Suwara
 */
public class Security2 {

    //hashed passwords to look for
    private static String hash_value1 = "6f047ccaa1ed3e8e05cde1c7ebc7d958";
    private static String hash_value2 = "275a5602cd91a468a0e10c226a03a39c";
    private static String hash_value3 = "b4ba93170358df216e8648734ac2d539";
    private static String hash_value4 = "dc1c6ca00763a1821c5af993e0b6f60a";
    private static String hash_value5 = "8cd9f1b962128bd3d3ede2f5f101f4fc";
    private static String hash_value6 = "554532464e066aba23aee72b95f18ba2";
    
    private static String entry;
    private static long start, end;
    private static double elapsed;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //ask user for file input
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter file path with double backslashes\n(Ex. C:\\\\Users\\\\user\\\\Desktop\\\\passwordDictionary.txt):");
        String fileName = reader.next();
        
        //start the timer 
        start = System.nanoTime(); 
        //read the dictionary
        FileReader dictionary = new FileReader(fileName);  
        try(BufferedReader dc = new BufferedReader(dictionary)){   
            //read a line at a time in the dictionary
            while((entry = dc.readLine()) != null){  
                //convert the dictionary entry to MD5
                String converted = getMD5(entry);
                //enter if only when converted entry equals one of the password hashes
                if ((converted.equals(hash_value1))||(converted.equals(hash_value2))||(converted.equals(hash_value3))||(converted.equals(hash_value4))||(converted.equals(hash_value5))||(converted.equals(hash_value6))) { 
                    //set end of search time
                    end = System.nanoTime();
                    //calculate elapsed time
                    elapsed = (double)(end - start)/1000000000; 
                    //print output
                    System.out.println(printStatement(converted, entry, elapsed));
                    //reset the timer
                    start = System.nanoTime();
                }
            }
        }
    }

    //This method puts the output String together
    public static String printStatement(String has, String hacked, double newtime) {
        String ths = "The password for hash value " + has + " is " + hacked + ",it takes the program " + newtime + " sec to recover this password.";
        //return the output string
        return ths;
    }

    //This method uses the MD5 Algorithm to convert the dictionary
    public static String getMD5(String oldLine) {
        //get bytes from the dictionary entry
        byte [] lineBytes= oldLine.getBytes();
        try {
            //create new message digest that uses the MD5 algorithm
            MessageDigest converter = MessageDigest.getInstance("MD5");
            //convert the line's bytes into array in MD5 and store in converted
            byte[] converted = converter.digest(lineBytes);
            //change converted to BigInteger to take advantage of BigInteger's
            //toString method
            BigInteger numRep = new BigInteger(1, converted);
            //use BigInteger's toString method to convert the BigInteger 
            //representation of the bytes into a hexidecimal representation of 
            //the bytes
            String hexString = numRep.toString(16);
            //return the hexString
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            return "NoSuchAlgorithm";
        }
    }
}
