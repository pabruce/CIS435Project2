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
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        RSACipher rsa = new RSACipher();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Step # 1 - Test RSA Cipher Encryption()");
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        // encrypt
        byte[] encrypted = rsa.encrypt(teststring.getBytes());
        // decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        
        System.out.println("Step # 2 - Test RSA Cipher Decryption()");
        System.out.println("Decrypting Bytes: " + bytetoStringConversion(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }
}
