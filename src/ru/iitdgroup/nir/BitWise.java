package ru.iitdgroup.nir;

public class BitWise {
    public static void main(String[] args) {

        //0x12 == 18 == 0010010
        //0x01 ==  1 == 0000001
        // & operator
        //              0000000
        System.out.println( 0x12 & 0x01);

        //0x12 == 18 == 0010010
        //0x02 ==  2 == 0000010
        // & operator
        //              0000010 == 2
        System.out.println( 0x12 & 0x02);

        //TODO: learn "|" and "^"

    }
}
