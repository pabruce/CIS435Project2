/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Forrest
 */
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
public class KeyGenTest
{
    public static void main(String[] args)
    {
        KeyGen gen = new KeyGen();
        KeyPair key = gen.GenerateKeyPair();
        System.out.println(key.bigboy[0]);
        System.out.println(key.bigboy[1]);
        System.out.println(key.bigboy[2]);
        
    }
}