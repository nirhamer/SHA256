package ru.iitdgroup.nir;


import java.io.FileInputStream;
import java.security.MessageDigest;

public class SHA256 {

    public static void main(String args[]) throws Exception {


        //region Print command line arguments
        System.out.println("Argument count: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + i + ": " + args[i]);
        }
        //endregion

       //C:\java projects\second-project\Demo\Root file.txt

        String filepath = "C:\\java projects\\second-project\\Demo\\Root file.txt";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        FileInputStream fileInput = new FileInputStream(filepath);
        byte[] dataBytes = new byte[1024];

        int bytesRead = 0;

        while ((bytesRead = fileInput.read(dataBytes)) != -1) {
            messageDigest.update(dataBytes, 0, bytesRead);
        }


        byte[] digestBytes = messageDigest.digest();

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
        return Integer.toString((digestByte & 0xff) + 0x100, 16).substring(1);
    }
}