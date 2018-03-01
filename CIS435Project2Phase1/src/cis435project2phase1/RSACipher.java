 /*
 * This class the standard RSA algorithm we used in class to convert to bytes. 
 * It takes the bytes into an array and then decrypts the bytes into strings. 
 * 
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
    
}
