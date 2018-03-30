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
public class Sender1 
{   
    String name = "Sender1";
    
    BigInteger N;
    BigInteger E;
    BigInteger D;
    
    byte[] certificate;
    byte[] symKey;
    byte[] message;
    byte[] encrypted;
    
    public Sender1()
    {
        System.out.println("Created Sender");
    }
    
    public void generateMessage()
    {
        String teststring = "Hello World. Test 1: Antelope Buffalo Cheeta";
        message = teststring.getBytes();
    }
    
    public void setSymetricKey(byte[] symetricKey)
    {
        symKey = symetricKey;
    }
        
    public void generateKeys()
    {
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        N = key.bigboy[2];
        E = key.bigboy[1];
        D = key.bigboy[0];
    }
    
    public void registerWithCA(LocalCA auth)
    {
        certificate = auth.signKeys(name, D, N);
    }
    
    public void sendPacketToNetwork(NetworkFinal network)
    {
        network.getPacketFromSender(encrypted);
    }
    
    public void encrypt(BigInteger ReceiverN, BigInteger ReceiverE) throws Exception
    {
        byte[] symMessage;
        BlockCipher blockCipher = new BlockCipher(symKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = blockCipher.blockEncrypt(message);
        
        encrypted = digSig.encrypt(N, ReceiverN, D, ReceiverE, symMessage);
    }
}

