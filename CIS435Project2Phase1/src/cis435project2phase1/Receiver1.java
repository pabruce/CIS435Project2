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
public class Receiver1 
{ 
    String name = "Receiver1";
    
    BigInteger N;
    BigInteger E;
    BigInteger D;
    
    byte[] certificate;
    byte[] symKey;
    byte[] encrypted;
    byte[] plaintext;
    
    public Receiver1()
    {
        System.out.println("Creating Receiver");
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
    
    public void receivePacketFromNetwork(NetworkFinal network)
    {
        encrypted = network.deliverPacketToReceiver();
    }
    
    public void decrypt(BigInteger SenderN, BigInteger SenderE) throws Exception
    {
        byte[] symMessage;
        BlockCipher blockCipher = new BlockCipher(symKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = digSig.decrypt(SenderN, N, SenderE, D, encrypted);
        plaintext = blockCipher.blockDecrypt(symMessage);
    }
}