/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import cis435project2phase1.SecureMessageSystemSimulation;
import cis435project2phase1.BlockCipher;
import cis435project2phase1.DigitalSignature;
import cis435project2phase1.Final;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.math.BigInteger;
import java.util.Arrays;
/**
 *
 * @author Patrick
 */
public class SenderFinal 
{   
    public SenderFinal()
    {
        System.out.println("Created Sender");
    }
    public byte[] encrypt(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderD, BigInteger ReceiverE, byte[] symetricKey, byte[] message) throws Exception
    {
        byte[] symMessage;
        byte[] aSymMessage;
        byte[] encrypted;
        BlockCipher blockCipher = new BlockCipher(symetricKey);
        DigitalSignature digSig = new DigitalSignature();
        Final encryptor = new Final();
       
        
        System.out.println("Step 1: Sender Generates Message");
        System.out.println("Message Generated = " + Arrays.toString(message) + "\n");
        
        
        System.out.println("Step 2: Block Cipher Message");
        symMessage = blockCipher.blockEncrypt(message);
        System.out.println("Block Cipher Encryption Message = " + symMessage + "\n");
        
        
        System.out.println("Step 3: Encryptin message with Sender's Private Key");
        aSymMessage = digSig.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, symMessage);
        System.out.println("Digital Signature = " + aSymMessage + "\n");
        
        System.out.println("Step 4: Generating Session Key");
        System.out.println("Session Key = " + symetricKey + "\n");
         
        System.out.println("Step 5: Encrypting the Session Key with Receiver's Public Key using RSA Algorithm");
        System.out.println("Receiver's N is: " + ReceiverN);
        System.out.println("Receiver's E is: " + ReceiverE);
        System.out.println("The Encrypted Session key with the receiver's public key = " + bytetoStringConversion(message) + "\n");
 
        System.out.println("Step 6: Encrypting the digital signature with Symmetric Key");
        encrypted = encryptor.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, symetricKey, message);
        System.out.println("Ciphered RSAed and Signed String: " + bytetoStringConversion(encrypted));
        
        System.out.println("Step 7: Packet to be sent to network is :" + encrypted);
        
        return encrypted;
        
    }
}

