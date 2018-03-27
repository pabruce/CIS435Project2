/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.math.BigInteger;
import cis435project2phase1.RSACipher;
import java.io.DataInputStream;
import java.io.IOException;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;

import static cis435project2phase1.RSACipher.*;
import java.math.BigInteger;
import cis435project2phase1.BlockCipher;
import cis435project2phase1.DigitalSignature;
import cis435project2phase1.Final;
import cis435project2phase1.SecureMessageSystemSimulation;
/**
 *
 * @author Patrick
 */
public class ReceiverFinal 
{ 
    byte[] receivePacket;
    
    public ReceiverFinal()
    {
        System.out.println("Creating Receiver");
        
    }
    public byte[] processPacket(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderE, BigInteger ReceiverD, byte[] symetricKey, byte[] message) throws Exception
    {
        byte[] symMessage;
        byte[] plaintext;
        byte[] decrypted; 
        Final encryptor = new Final();
        BlockCipher blockCipher = new BlockCipher(symetricKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = digSig.decrypt(SenderN, ReceiverN, SenderE, ReceiverD, message);
        
        plaintext = blockCipher.blockDecrypt(symMessage);
        
        
        System.out.println("Step 1: Receiver gets the packet from network");
        System.out.println(symMessage);
        
        System.out.println("Step 2: Decrypt Message using decryption algorithm");
        System.out.println(plaintext);
         return plaintext;      
    }
}