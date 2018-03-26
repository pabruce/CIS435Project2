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

/**
 *
 * @author Patrick
 */
public class ReceiverFinal 
{
    public byte[] receivePacket(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderD, BigInteger ReceiverE, byte[] symetricKey, byte[] message) throws Exception
    {
      aSymMessage = digSig.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, symMessage);
    }
    
    public byte[] processPacket(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderE, BigInteger ReceiverD, byte[] symetricKey, byte[] message) throws Exception
    {
        byte[] symMessage;
        byte[] plaintext;
        BlockCipher blockCipher = new BlockCipher(symetricKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = digSig.decrypt(SenderN, ReceiverN, SenderE, ReceiverD, message);
        
        plaintext = blockCipher.blockDecrypt(symMessage);
        
        return plaintext;        
    }
    
     public String getMessage()
     {
         
     }
    
}