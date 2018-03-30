/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.math.BigInteger;

/**
 *
 * @author Patrick
 */
public class Sender2 
{   
    String name = "Sender2";
    
    BigInteger N;
    BigInteger E;
    BigInteger D;
    
    byte[] certificate;
    byte[] symKey;
    byte[] message;
    byte[] encrypted;
    
    public Sender2()
    {
        System.out.println("Created Sender");
    }
    
    public void generateMessage()
    {
        String teststring = "Hello World. Test 1: Antelope Buffalo Cheeta";
        message = teststring.getBytes();
        
        System.out.printf("%-80s %s %n", "Message Generated", teststring);
    }
    
    public void setSymetricKey(byte[] symetricKey)
    {
        symKey = symetricKey;
        
        System.out.printf("%-80s %s %n", "Symetric key shared with Sender", RSACipher.bytetoStringConversion(symKey));
    }
        
    public void generateKeys()
    {
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        N = key.bigboy[2];
        E = key.bigboy[1];
        D = key.bigboy[0];
        
        System.out.printf("%s %n", "Sender Keypair generated: ");
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
    
    public void sendPacketToNetwork(NetworkFinal network)
    {
        network.getPacketFromSender(encrypted);
        
        System.out.printf("%-80s %s %n", "Message sent", RSACipher.bytetoStringConversion(encrypted));
    }
    
    public void encrypt(BigInteger ReceiverN, BigInteger ReceiverE) throws Exception
    {
        byte[] symMessage;
        BlockCipher blockCipher = new BlockCipher(symKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = blockCipher.blockEncrypt(message);
        
        encrypted = digSig.encrypt(N, ReceiverN, D, ReceiverE, symMessage);
        
        System.out.printf("%-80s %s %n", "Message encrypted with aes rsa and signed", RSACipher.bytetoStringConversion(encrypted));
    }
}

