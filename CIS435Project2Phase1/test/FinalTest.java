/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anthonyweber
 */

import cis435project2phase1.Final;
import cis435project2phase1.Final.*;
import static cis435project2phase1.Final.bytetoStringConversion;
import java.nio.charset.StandardCharsets;
import java.io.DataInputStream;
import java.io.IOException;

public class FinalTest
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, Exception
    {
        Final rsa = new Final();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Step # 1 - Test RSA Cipher Encryption()");
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        // encrypt
        byte[] encrypted = rsa.encrypt(teststring.getBytes());
        
        byte[] encryptionKey = "MZytUvNdHCpFroLb".getBytes(StandardCharsets.UTF_8);
        byte[] plainText = teststring.getBytes(StandardCharsets.UTF_8);
        Final advEncrStndrd = new Final(encryptionKey);
        byte[] cipherText = advEncrStndrd.blockEncrypt(plainText);
        
        System.out.println("Step # 2 - Test Block Cipher Encryption()");
        System.out.println("Plain Text message: " +new String(plainText));
        System.out.println("Result: " +new String(cipherText));
        
        byte[] decrypted = rsa.decrypt(encrypted);
        
        
        System.out.println("Step # 3 - Test RSA Cipher Decryption()");
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
        
        byte[] decryptedCipherText = advEncrStndrd.blockDecrypt(cipherText);
        
        System.out.println("Step # 4 - Test Block Cipher Decryption()");
        System.out.println("Expected result:" + teststring);
        System.out.println("Result: "  +new String(decryptedCipherText));
        
    }
    
}
