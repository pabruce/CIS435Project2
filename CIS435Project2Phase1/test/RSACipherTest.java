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

import static cis435project2phase1.RSACipher.*;

public class RSACipherTest 
{
    public static void main(String[] args) throws IOException
    {
        RSACipher rsa = new RSACipher();
        DataInputStream in = new DataInputStream(System.in);
        String plainText;
        System.out.println("Enter the plain text:");
        plainText = in.readLine();
        System.out.println("Step # 1 - Test RSA Cipher Encryption()");
        System.out.println("Encrypting String: " + plainText);
        System.out.println("String in Bytes: " + bytetoStringConversion(plainText.getBytes()));
        
        // encrypt
        byte[] encrypted = rsa.rsaEncrypt(plainText.getBytes());
        // decrypt
        byte[] decrypted = rsa.rsaDecrypt(encrypted);
        
        System.out.println("Step # 2 - Test RSA Cipher Decryption()");
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }
}
