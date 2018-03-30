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

import java.io.IOException;


public class SecureMessageSystemSimulation 
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, Exception
    {
        byte[] symetricKey = "MZytUvNdHCpFroLb".getBytes();
        
        
        //Step 1 create sender and receiver and CA
        Sender1 sender1 = new Sender1();
        Receiver1 receiver1 = new Receiver1();
        LocalCA ca = new LocalCA();
        NetworkFinal net = new NetworkFinal();
        
        //Step 2 have sender and receiver generate keys
        sender1.generateKeys();
        receiver1.generateKeys();
        sender1.setSymetricKey(symetricKey);
        receiver1.setSymetricKey(symetricKey);
        
        //Step 3 have sender and receiver register with CA
        sender1.registerWithCA(ca);
        
        //Step 4 Sender encrypts and sends message
        sender1.generateMessage();
        sender1.encrypt(receiver1.N, receiver1.E);
        sender1.sendPacketToNetwork(net);
        
       // net.packetGetHacked();
        
        //Step 5 Receiver gets and decrypts message
        receiver1.receivePacketFromNetwork(net);
        receiver1.decrypt(sender1.N, sender1.E);
        System.out.println(new String(receiver1.plaintext));
        
        
    }
}
