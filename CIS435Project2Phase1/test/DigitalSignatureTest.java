
import cis435project2phase1.KeyGen;
import cis435project2phase1.RSACipher;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;
import cis435project2phase1.KeyPair;
import cis435project2phase1.SimpleHash;
import cis435project2phase1.DigitalSignature;


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
        KeyPair keyPos;
        KeyGen keyG = new KeyGen();
        RSACipher rsa = new RSACipher();
        DataInputStream in = new DataInputStream(System.in);
        SimpleHash hasher = new SimpleHash();
        DigitalSignature digSig = new DigitalSignature();
        
        keyPos = keyG.GenerateKeyPair();
        String teststring;
        
        byte[] message;
        BigInteger SenderE, SenderD, SenderN, ReceiverE, ReceiverD, ReceiverN;
        
        
        //d is private key
        //e is public key
        
        //d= 0,e = 1,n = 2
        SenderN = keyPos.bigboy[2];
        SenderE = keyPos.bigboy[1];
        SenderD = keyPos.bigboy[0];
        
        ReceiverN = keyPos.bigboy[2];
        ReceiverE = keyPos.bigboy[1];
        ReceiverD = keyPos.bigboy[0];
        
        
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        message = digSig.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, teststring.getBytes());
        
        digSig.decrypt(SenderN, ReceiverN, SenderE, ReceiverD, message);
        
        
        
//        // encrypt
//        byte[] encrypted = rsa.encrypt2(teststring.getBytes(), ReceiverE, ReceiverN);
//        System.out.println("Encrypted Bytes: " + encrypted);
//        byte[] signed = new byte[encrypted.length + 1];
//        byte[] hashed = new byte[]{hasher.hash(teststring.getBytes())};
//        hashed = rsa.encrypt2(hashed, SenderD, SenderN);
//        System.arraycopy(encrypted, 0, signed, 0, encrypted.length);
//        System.arraycopy(hashed, 0, signed, encrypted.length, 1);
//        
//        System.out.println(signed);
//        
//        
//        
//        //decrypt
//        
//        System.arraycopy(signed, 0, encrypted, 0, signed.length-1);
//        System.arraycopy(signed, signed.length-1, hashed, 0, 1);
//        
//        hashed = rsa.decrypt2(hashed, SenderE, SenderN);
//        byte[] hashcheck = new byte[]{hasher.hash(rsa.decrypt2(encrypted, ReceiverD, ReceiverN))};
//        if (hashcheck == hashed)
//        {
//            System.out.println("success");
//        }
//        else
//        {
//            System.out.println(hashcheck);
//            System.out.println(hashed);
//            
//        }
        
        
        
//        byte[] decrypted = rsa.decrypt2(encrypted, SenderD, SenderN);
//        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
//        System.out.println("Decrypted String: " + new String(decrypted));
    }   
}
