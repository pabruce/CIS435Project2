/*
 * RSACipher test takes a plain text, encrypts it with the RSA class
 * It decrypts using a converter from Byte to String output
 * The string output handles any input and encrypts it using bytes and decrypts
 */

/**
 *
 * @author Patrick Bruce
 * @date 2/28/17
 */

import cis435project2phase1.RSACipher;
import java.io.DataInputStream;
import java.io.IOException;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;

import static cis435project2phase1.RSACipher.*;
import java.math.BigInteger;

public class RSACipherTest 
{
    public static void main(String[] args) throws IOException
    {
        KeyPair keyPos;
        RSACipher rsa = new RSACipher();
        KeyGen keyG = new KeyGen();
        
        keyPos = keyG.GenerateKeyPair();
        
        DataInputStream in = new DataInputStream(System.in);
        
        String plainText;
        
        plainText = in.readLine();
        
        System.out.println("Step # 1 - Test RSA Cipher Encryption()");
        System.out.println("Encrypting String: " + plainText);
        System.out.println("String in Bytes: " + bytetoStringConversion(plainText.getBytes()));
        
        byte[] encrypted = rsa.encrypt2(plainText.getBytes(), keyPos.bigboy[1], keyPos.bigboy[2]);
        byte[] decrypted = rsa.decrypt2(encrypted, keyPos.bigboy[0], keyPos.bigboy[2]);
        
        System.out.println("Step # 2 - Test RSA Cipher Decryption()");
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
  
    }
}
