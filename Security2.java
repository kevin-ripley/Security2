package security2;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    
    private static String new_hash, dictionary, password;
    private static int count;
    private static long start, end;
    private static double elapsed;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //read the converted dictionary
        FileReader hashed_file = new FileReader("C:\\Users\\smart_000\\Desktop\\output.txt");
        try(BufferedReader hf = new BufferedReader(hashed_file)){
            //start the timer
            start = System.nanoTime();       
            //read a line at a time in the converted dictionary
            while((new_hash = hf.readLine()) != null){  
                //add to the line count
                count++;
                //enter if only when dictionary hash equals one of the password hashes
                if ((new_hash.equals(hash_value1))||(new_hash.equals(hash_value2))||(new_hash.equals(hash_value3))||(new_hash.equals(hash_value4))||(new_hash.equals(hash_value5))||(new_hash.equals(hash_value6))) { 
                    //find the unhashed password
                    password = findLine(count);
                    //set end of search time
                    end = System.nanoTime();
                    //calculate elapsed time
                    elapsed = (double)(end - start)/1000000000; 
                    //print output
                    System.out.println(printStatement(new_hash, password, elapsed));
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
    
    //This method finds the password in the unhashed dictionary using the line number from the hashed dictionary
    public static String findLine(int lineNum) throws IOException{
        //read in dictionary
        FileReader dictionary_file = new FileReader("C:\\Users\\smart_000\\Desktop\\realhuman_phill.txt");
        try(BufferedReader dc = new BufferedReader(dictionary_file)){  
            //read until the password line
            for(int i = 1; i < lineNum; i++){
                dc.readLine();
            }
            //get the password
            String line = dc.readLine();
            //return the password
            return line;
        }
    }

    //WE CONVERTED THE FILE BEFORE GIVING THE PROGRAM TO YOU
    //SO WE DO NOT CALL THIS METHOD IN main
    
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

    //WE CONVERTED THE FILE BEFORE GIVING THE PROGRAM TO YOU
    //SO WE DO NOT CALL THIS METHOD IN main
    
    //This method creates a new txt file with the hash values
    public static void convert() throws IOException {
        //read in dictionary and set up buffer
        FileReader dict = new FileReader("/Users/kevinripley/Desktop/realhuman_phill.txt");
        BufferedReader dr = new BufferedReader(dict);

        //read through dictionary
        while ((dictionary = dr.readLine()) != null) {
            //convert dictionary to MD5 and store in diction
            String diction = getMD5(dictionary);
            //output the dictionary hash values of the dictionary to an empty txt file
            File outFile = new File("/Users/kevinripley/Desktop/output.txt");
            FileWriter fWriter;
            fWriter = new FileWriter(outFile, true);
            try (PrintWriter pWriter = new PrintWriter(fWriter)) {
                pWriter.println(diction);
            }
        }
    }
}
