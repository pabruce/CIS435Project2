/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase2;

import java.math.BigInteger;

/**
 *
 * @author Patrick
 */
public class Receiver2 
{ 
    String name = "Receiver1";
    
    BigInteger N;
    BigInteger E;
    BigInteger D;
    
    byte[] certificate;
    int symKey;
    byte[] encrypted;
    byte[] plaintext;
    
    public Receiver2()
    {
        System.out.println("Creating Receiver");
    }
    
    public void setSymetricKey(int symetricKey)
    {
        symKey = symetricKey;
        
        System.out.printf("%-80s %d %n", "Symetric key shared with Receiver", symKey);
    }
    
    public void generateKeys()
    {
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        N = key.bigboy[2];
        E = key.bigboy[1];
        D = key.bigboy[0];
        
        System.out.printf("%s %n", "Receiver Keypair generated: ");
        System.out.printf("%-20s %s %n", "Private Key", D);
        System.out.printf("%-20s %s %n", "Public Key", E);
        System.out.printf("%-20s %s %n", "Nvalue", N);
        System.out.println();

    }
    
    public void registerWithCA(LocalCA auth)
    {
        certificate = auth.signKeys(name, D, N);
        
        System.out.printf("%s %n", RSACipher.bytetoStringConversion(certificate));
    }
    
    public void receivePacketFromNetwork(NetworkFinal network)
    {
        encrypted = network.deliverPacketToReceiver();
        
        System.out.printf("%-80s %s %n", "Message received", RSACipher.bytetoStringConversion(encrypted));
    }
    
    public void decrypt(BigInteger SenderN, BigInteger SenderE) throws Exception
    {
        byte[] symMessage;
        DigitalSignature digSig = new DigitalSignature();
        ShiftCipher sc = new ShiftCipher();
        
        symMessage = digSig.decrypt(SenderN, N, SenderE, D, encrypted);
        if (symMessage == null)
        {
            String error = "invalid input";
            plaintext = error.getBytes();
            return;
        }
        plaintext = ShiftCipher.decryptShiftCipher(new String(symMessage), symKey).getBytes();
        
        System.out.printf("%-80s %s %n", "Message decrypted with aes rsa and signature checked", RSACipher.bytetoStringConversion(plaintext));
    }
}