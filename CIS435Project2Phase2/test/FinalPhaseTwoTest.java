/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
import java.io.DataInputStream;
import java.io.IOException;
import cis435project2phase1.Final;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class FinalPhaseTwoTest
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, Exception
    {
        DataInputStream in = new DataInputStream(System.in);
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        Final encryptor = new Final();
        
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
        
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        encrypted = encryptor.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, symetricKey, teststring.getBytes());
        
        System.out.println("Ciphered RSAed and Signed String: " + bytetoStringConversion(encrypted));
        
        decrypted = encryptor.decrypt(SenderN, ReceiverN, SenderE, ReceiverD, symetricKey, encrypted);
        
        System.out.println("Decrypted String: " + new String(decrypted));
    }
}
