package cis435project2phase1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bruce
 */

import java.io.DataInputStream;
import java.io.IOException;
import cis435project2phase1.SenderFinal;
import cis435project2phase1.ReceiverFinal;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
import static cis435project2phase1.RSACipher.bytetoStringConversion;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class SecureMessageSystemSimulation 
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, Exception
    {
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        SenderFinal encryptor = new SenderFinal();
        ReceiverFinal decryptor = new ReceiverFinal();
        SenderFinal sender = new SenderFinal();
        ReceiverFinal receiver = new ReceiverFinal();
        NetworkFinal network = new NetworkFinal();
        
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
        System.out.println("\n ------Sender sends the test packet to Receiver through internet" + "\n");
        
        network.sendPacketToReceiver(encrypted);
    }
}
