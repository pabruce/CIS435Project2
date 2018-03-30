/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.math.BigInteger;
        

/**
 *
 * @author dghelardini
 */
import cis435project2phase1.RSACipher;
import java.io.DataInputStream;
import java.nio.ByteBuffer;


public class DigitalSignature
{
    public byte[] encrypt(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderD, BigInteger ReceiverE, byte[] message)
    {
        RSACipher rsa = new RSACipher();
        SimpleHash hasher = new SimpleHash();
        
        byte[] hash;
        byte[] signedHash;
        byte[] encrypted;
        byte[] output;
        byte[] len;
        
        //step 1 hash message store in hash
        hash = new byte[]{hasher.hash(message)};
        
        //step 2 encrypt hash with senders private key store in signedHash
        signedHash = rsa.encrypt2(hash, SenderD, SenderN);
        
        //step 3 encrypt message with receivers public key store in encrypted
        encrypted = rsa.encrypt2(message, ReceiverE, ReceiverN);
        
        //step 4 concatenate into output
        output = new byte[encrypted.length + signedHash.length + 4];
        len = new byte[4];
        len = ByteBuffer.allocate(4).putInt(signedHash.length).array();
        System.arraycopy(encrypted, 0, output, 0, encrypted.length);
        System.arraycopy(signedHash, 0, output, encrypted.length, signedHash.length);
        System.arraycopy(len, 0, output, encrypted.length + signedHash.length, 4);
                
        return output;
    }
    
    
    public byte[] decrypt(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderE, BigInteger ReceiverD, byte[] message)
    {
        RSACipher rsa = new RSACipher();
        SimpleHash hasher = new SimpleHash();
        
        byte[] encrypted;
        byte[] signedHash;
        byte[] decryptedHash;
        byte[] messageHash;
        byte[] plaintext;
        byte[] len = new byte[4];
        int length;
        
        //step 1 break message into encrypted and signedHash
        System.arraycopy(message, message.length - 4, len, 0, 4);
        length = ByteBuffer.wrap(len).getInt();
        encrypted = new byte[message.length - length - 4];
        signedHash = new byte[length];
        System.arraycopy(message, 0, encrypted, 0, message.length - length - 4);
        System.arraycopy(message, message.length - length - 4, signedHash, 0, length);
        
        //step 2 decrypt signedHash into decryptedHash
        decryptedHash = rsa.decrypt2(signedHash, SenderE, SenderN);
        
        //step 3 decrypt encrypted into plaintext
        plaintext = rsa.decrypt2(encrypted, ReceiverD, ReceiverN);
        
        //step 4 hash plaintext into messageHash
        messageHash = new byte[]{hasher.hash(plaintext)};
//        System.out.println(RSACipher.bytetoStringConversion(messageHash));    //for debug
//        System.out.println(RSACipher.bytetoStringConversion(decryptedHash));
        
        //step 5 compare messageHash and decryptedHash
        if (RSACipher.bytetoStringConversion(messageHash).equals(RSACipher.bytetoStringConversion(decryptedHash)))
        {
            System.out.println("The Hashes match, message valid");
            return plaintext;
        }
        else
        {
            String error = "The Hashes are incorrect, invalid message";
            System.out.println(error);
            return null;
        }
    }
}
