 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

/**
 *
 * @author Patrick Bruce
 * @date   2/28/18
 */


import java.math.BigInteger;
import java.util.Random;



public class RSACipher
{
    private BigInteger p,q,n,e,d,ph;
    private Random rand;
    private int bitlength = 1024;
    
    public RSACipher()
    {
        rand = new Random();
        p = BigInteger.probablePrime(bitlength, rand);
        q = BigInteger.probablePrime(bitlength, rand);
        n = p.multiply(q);
        ph = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2,rand);
        
        while (ph.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(ph) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(ph);
    }
    
    public RSACipher(BigInteger e,BigInteger d, BigInteger n)
    {
        this.e = e;
        this.d = d;
        this.n = n;
    }
    
     public static String bytetoStringConversion(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    public byte[] encrypt(byte[] m)
    {
        return (new BigInteger(m)).modPow(e, n).toByteArray();
    }
 
    public byte[] decrypt(byte[] m)
    {
        return (new BigInteger(m)).modPow(d, n).toByteArray();
    }
    
    public byte[] encrypt2(byte[] m, BigInteger e, BigInteger n)
    {
        return (new BigInteger(m).modPow(e, n).toByteArray());
    }
    
    public byte[] decrypt2(byte[] m, BigInteger d, BigInteger n)
    {
        return (new BigInteger(m)).modPow(d, n).toByteArray();
    }
}
