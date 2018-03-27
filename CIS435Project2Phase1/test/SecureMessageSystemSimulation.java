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
        
        System.out.println("Step 1: Generate Message");
        teststring = "Hello World";
        System.out.println("Encrypting String: " + teststring);

        System.out.println("String in Bytes: " + bytetoStringConversion(teststring.getBytes()));
        
        encrypted = encryptor.processMessage(SenderN, ReceiverN, SenderD, ReceiverE, symetricKey, teststring.getBytes());
        
        System.out.println("Ciphered RSAed and Signed String: " + bytetoStringConversion(encrypted));
        
        decrypted = decryptor.processPacket(SenderN, ReceiverN, SenderE, ReceiverD, symetricKey, encrypted);
        
        System.out.println("Decrypted String: " + new String(decrypted));
        
    
    }
    
    /*
    	KeyGen en = new KeyGen();
    	Sender amy = new Sender(en);
        Receiver bob = new Receiver();
        Network net = new Network();      

        System.out.println("\n ------Sender sends the test packet to Receiver through internet" + "\n");
        
        net.sendToReceiver(amy.getPacket());
        
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||| ");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||| ");
        
        net.setInternetCondition(0);
        
        System.out.println("------Asumme perfect Internet with no error----- ");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||| ");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||| " + "\n");

        
       System.out.println("------Receiver receives the test packet through network");
        
       bob.receive(net, en);

    }*/
}
