
import cis435project2phase1.KeyGen;
import cis435project2phase1.RSACipher;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dghelardini
 */

import java.math.BigInteger;

public class DigitalSignatureTest 
{
        @SuppressWarnings("deprecation")
      public static void main(String args[]) throws IOException
    {
        BigInteger[] keyPos = new BigInteger[3];
        KeyGen keyG = new KeyGen();
        RSACipher rsa = new RSACipher();
        DataInputStream in = new DataInputStream(System.in);
        
        keyPos = keyG.GenerateKeyPair();
        String teststring;
        
        String message;
        BigInteger SenderE, SenderD, SenderN, ReceiverE, ReceiverD, ReceiverN;
        
        //d= 0,e = 1,n = 2
        SenderN = keyPos[2];
        SenderE = keyPos[1];
        SenderD = keyPos[0];
        
        ReceiverN = keyPos[2];
        ReceiverE = keyPos[1];
        ReceiverD = keyPos[0];
        
        
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        // encrypt
        byte[] encrypted = rsa.encrypt2(teststring.getBytes(), SenderE, SenderN);
        // decrypt
        System.out.println("Encrypted Bytes: " + encrypted);
        byte[] decrypted = rsa.decrypt2(encrypted, SenderD, SenderN);
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }   
}
