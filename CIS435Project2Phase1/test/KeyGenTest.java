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
import cis435project2phase1.KeyGen.*;
public class KeyGenTest
{
    public static void main(String[] args)
    {
        KeyGen gen = new KeyGen();
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.generateRandomBigPrime());
        System.out.println(gen.GenerateKeyPair());
        System.out.println("There you go, a bunch of primes");        
    }
}