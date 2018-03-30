package cis435project2phase1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bruce
 */

import java.io.DataInputStream;
import java.io.IOException;
import cis435project2phase1.SenderFinal;
import cis435project2phase1.ReceiverFinal;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class SecureMessageSystemSimulation 
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, Exception
    {
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        SenderFinal encryptor = new SenderFinal();
        ReceiverFinal decryptor = new ReceiverFinal();
        SenderFinal sender = new SenderFinal();
        ReceiverFinal receiver = new ReceiverFinal();
        NetworkFinal network = new NetworkFinal();
        
        String teststring;
        byte[] encrypted;
        byte[] symetricKey = "MZytUvNdHCpFroLb".getBytes(StandardCharsets.UTF_8);
        byte[] decrypted;
        BigInteger SenderN = key.bigboy[2];
        BigInteger SenderE = key.bigboy[1];
        BigInteger SenderD = key.bigboy[0];
        
        BigInteger ReceiverN = key.bigboy[2];
        BigInteger ReceiverE = key.bigboy[1];
        BigInteger ReceiverD = key.bigboy[0];
        System.out.println("\n ------Sender sends the test packet to Receiver through internet" + "\n");
        
        
        teststring = "Hello World";
        
        //Step 1 create sender and receiver
        //Step 2 have sender and receiver generate keys
        //Step 3 create CA
        //Step 4 have sender and receiver send keys to CA
        //Step 5 (optional) have sender and receiver validate eachotehrs identity with CA
        //Step 6 Create Network
        //Step 7 send encrypted message over network to receiver
        //Step 8 Receiver decrypts message
        
    }
}
