/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.math.BigInteger;



/**
 *
 * @author Forrest
 */
public class LocalCA {
    BigInteger N;
    BigInteger E;
    BigInteger D;
    
    RSACipher rsa;
    
    public LocalCA()
    {
        System.out.println("Certificate Authority online");
        
        KeyGen keyGen = new KeyGen();
        KeyPair key = keyGen.GenerateKeyPair();
        N = key.bigboy[2];
        E = key.bigboy[1];
        D = key.bigboy[0];
        
    }
    
    public byte[] signKeys(String name, BigInteger k1, BigInteger k2)
    {
        rsa = new RSACipher();
        String out = name + " has registered their public key and received a signed certificate";
        System.out.printf("%-81s", out);
        name = name + k1 + k2;
        return rsa.encrypt2(name.getBytes(), E, N);
    }
    
}
