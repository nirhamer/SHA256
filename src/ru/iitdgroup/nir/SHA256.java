package ru.iitdgroup.nir;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA256 {
    /**
     *
     * @param args when a user starts the program via command line he can give it arguments
     * as text
     * like
     * javac SHA256.java
     * java SHA256 hello world what up  as an argument
     * in the code
     * the args
     * its an array containing all the user provided arguments
     * this code would print out
     * java Foo C://bla/foo/bar/data.txt
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.println(Arrays.toString(args));



        System.out.println("Argument count: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + i + ": " + args[i]);
        }
        //endregion

        String path = args[0];

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        FileInputStream fileInput = new FileInputStream(path);
        byte[] dataBytes = new byte[1024];

        int bytesRead = 0;

        //region Read the file from the file stream until end  of the file (read returns -1)
        while ((bytesRead = fileInput.read(dataBytes)) != -1) {
            messageDigest.update(dataBytes, 0, bytesRead);
        }
        //endregion


        byte[] digestBytes = messageDigest.digest();

        //TODO: what is the difference between StringBuilder and StringBuffer
        /*
        StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously.
        StringBuilder is non-synchronized i.e. not thread safe. It means two threads can call the methods of StringBuilder simultaneously.
        therefore StringBuilder is more efficient than StringBuffer
         */
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < digestBytes.length; i++) {
            sb.append(byteToString(digestBytes[i]));
        }

        System.out.println("Checksum for the File: 0x" + sb.toString());

        fileInput.close();

    }

    /**
     * Convert byte to human-readable HEX letters
     * @param digestByte byte to convert
     * @return 2 characters string
     */
    protected static String byteToString(byte digestByte) {
        //TODO: what is the "magic" 0x100 ?

        //TODO: understand "&" operator https://www.baeldung.com/java-bitwise-operators
        /*
        Binary AND Operator copies a bit to the result if it exists in both operands.
        Example (A & B) will give 12 which is 0000 1100


        Bitwise operator works on bits and performs bit-by-bit operation. Assume if a = 60 and b = 13; now in binary format they will be as follows −

a = 0011 1100

b = 0000 1101

a&b = 0000 1100
        */
        return Integer.toString((digestByte & 0xff) + 0x100, 16).substring(1);
    }
}