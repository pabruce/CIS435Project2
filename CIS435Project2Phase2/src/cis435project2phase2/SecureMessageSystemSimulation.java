package cis435project2phase2;

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
    byte[] symetricKey = "MZytUvNdHCpFroLb".getBytes();
    int symetricKey2 = 12;
    String line = "-------------------------------------------------------------------------------------------------------------------------------";
    
    public void SecureMessageSystemSimulation(){};
    
    public void test1() throws Exception
    {
        System.out.println("Test 1: Block Cipher, RSA, DigSig. no hacker\n"+line);
        
        //Step 1 create sender and receiver and CA and network
        Sender1 sender1 = new Sender1();
        Receiver1 receiver1 = new Receiver1();
        LocalCA ca = new LocalCA();
        NetworkFinal net = new NetworkFinal();
        
        System.out.println(line);
        
        //Step 2 have sender and receiver generate keys and share symetric key
        sender1.generateKeys();
        receiver1.generateKeys();
        sender1.setSymetricKey(symetricKey);
        receiver1.setSymetricKey(symetricKey);
        
        System.out.println();
        
        //Step 3 have sender and receiver register with CA
        sender1.registerWithCA(ca);
        receiver1.registerWithCA(ca);
        
        System.out.println();
        
        //Step 4 Sender encrypts and sends message
        sender1.generateMessage();
        sender1.encrypt(receiver1.N, receiver1.E);
        sender1.sendPacketToNetwork(net);
        
        System.out.println("\t\t\t\t---No hackers or interference---");
                
        //Step 5 Receiver gets and decrypts message
        receiver1.receivePacketFromNetwork(net);
        receiver1.decrypt(sender1.N, sender1.E);
        
        System.out.printf("%-80s %s %n", "Message converted to String:", new String(receiver1.plaintext));
        System.out.println(line+"\n\n\n");
    }
    
    public void test2() throws Exception
    {
        System.out.println("Test 2: Block Cipher, RSA, DigSig. Altered message\n"+line);
        
        //Step 1 create sender and receiver and CA and network
        Sender1 sender1 = new Sender1();
        Receiver1 receiver1 = new Receiver1();
        LocalCA ca = new LocalCA();
        NetworkFinal net = new NetworkFinal();
        
        //Step 2 have sender and receiver generate keys and share symetric key
        sender1.generateKeys();
        receiver1.generateKeys();
        sender1.setSymetricKey(symetricKey);
        receiver1.setSymetricKey(symetricKey);
        
        System.out.println();
        
        //Step 3 have sender and receiver register with CA
        sender1.registerWithCA(ca);
        receiver1.registerWithCA(ca);
        
        System.out.println();
        
        //Step 4 Sender encrypts and sends message
        sender1.generateMessage();
        sender1.encrypt(receiver1.N, receiver1.E);
        sender1.sendPacketToNetwork(net);
        
        System.out.println("\t\t\t\t---Message Altered---");
        net.packetGetHacked();
                
        //Step 5 Receiver gets and decrypts message
        receiver1.receivePacketFromNetwork(net);
        receiver1.decrypt(sender1.N, sender1.E);
        
        System.out.printf("%-80s %s %n", "Message converted to String:", new String(receiver1.plaintext));
        System.out.println(line+"\n\n\n");
    }
    
    public void test3() throws Exception
    {
        System.out.println("Test 3: Chaining Block Cipher, RSA, DigSig. no hacker\n"+line);
        
        //Step 1 create sender and receiver and CA and network
        Sender2 sender1 = new Sender2();
        Receiver2 receiver1 = new Receiver2();
        LocalCA ca = new LocalCA();
        NetworkFinal net = new NetworkFinal();
        
        System.out.println(line);
        
        //Step 2 have sender and receiver generate keys and share symetric key
        sender1.generateKeys();
        receiver1.generateKeys();
        sender1.setSymetricKey(symetricKey2);
        receiver1.setSymetricKey(symetricKey2);
        
        System.out.println();
        
        //Step 3 have sender and receiver register with CA
        sender1.registerWithCA(ca);
        receiver1.registerWithCA(ca);
        
        System.out.println();
        
        //Step 4 Sender encrypts and sends message
        sender1.generateMessage();
        sender1.encrypt(receiver1.N, receiver1.E);
        sender1.sendPacketToNetwork(net);
        
        System.out.println("\t\t\t\t---No hackers or interference---");
                
        //Step 5 Receiver gets and decrypts message
        receiver1.receivePacketFromNetwork(net);
        receiver1.decrypt(sender1.N, sender1.E);
        
        System.out.printf("%-80s %s %n", "Message converted to String:", new String(receiver1.plaintext));
        System.out.println(line+"\n\n\n");
    }
    
    public void test4() throws Exception
    {
        System.out.println("Test 4: Chaining Block Cipher, RSA, DigSig. Message altered\n"+line);
        
        //Step 1 create sender and receiver and CA and network
        Sender2 sender1 = new Sender2();
        Receiver2 receiver1 = new Receiver2();
        LocalCA ca = new LocalCA();
        NetworkFinal net = new NetworkFinal();
        
        System.out.println(line);
        
        //Step 2 have sender and receiver generate keys and share symetric key
        sender1.generateKeys();
        receiver1.generateKeys();
        sender1.setSymetricKey(symetricKey2);
        receiver1.setSymetricKey(symetricKey2);
        
        System.out.println();
        
        //Step 3 have sender and receiver register with CA
        sender1.registerWithCA(ca);
        receiver1.registerWithCA(ca);
        
        System.out.println();
        
        //Step 4 Sender encrypts and sends message
        sender1.generateMessage();
        sender1.encrypt(receiver1.N, receiver1.E);
        sender1.sendPacketToNetwork(net);
        
        System.out.println("\t\t\t\t---Message Altered---");
        net.packetGetHacked();
                
        //Step 5 Receiver gets and decrypts message
        receiver1.receivePacketFromNetwork(net);
        receiver1.decrypt(sender1.N, sender1.E);
        
        System.out.printf("%-80s %s %n", "Message converted to String:", new String(receiver1.plaintext));
        System.out.println(line+"\n\n\n");
    }
}
