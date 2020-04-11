package ru.iitdgroup.nir;

import org.testng.Assert;
import sun.security.provider.SHA;

public class SHA256Test {

    @org.testng.annotations.Test
    public void testByteToString() {
        Assert.assertEquals(SHA256.byteToString((byte)0x00),"00");
        Assert.assertEquals(SHA256.byteToString((byte)0x01),"01");
        Assert.assertEquals(SHA256.byteToString((byte)0x2A),"2a");
        Assert.assertEquals(SHA256.byteToString((byte)0xFF),"ff");
    }
}