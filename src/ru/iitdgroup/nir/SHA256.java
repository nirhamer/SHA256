package ru.iitdgroup.nir;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA256 {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.println(Arrays.toString(args));


        //region Print command line arguments
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
        return Integer.toString((digestByte & 0xff) + 0x100, 16).substring(1);
    }
}