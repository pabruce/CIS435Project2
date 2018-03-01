/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick Bruce
 * @date 2/28/17
 */

import cis435project2phase1.RSACipher;
import java.io.DataInputStream;
import java.io.IOException;

import static cis435project2phase1.RSACipher.*;

public class RSACipherTest 
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        RSACipher rsa = new RSACipher();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        // encrypt
        byte[] encrypted = rsa.encrypt(teststring.getBytes());
        // decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }
}
