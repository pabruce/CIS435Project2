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
import cis435project2phase1.KeyGen;



public class RSACipher
{
/*    
    public RSACipher()
    {
        rand = new Random();
        
        p = BigInteger.probablePrime(bitlength, rand);
        q = BigInteger.probablePrime(bitlength, rand);
        n = p.multiply(q);
        toTient = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2,rand);
        
        while (toTient.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(toTient) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(toTient);
    }
    */

     public static String bytetoStringConversion(byte[] encrypt)
    {
        String plaintextTester = "";
        
        for (byte b : encrypt)
        {
            plaintextTester += Byte.toString(b);
        }
        return plaintextTester;
    }
 /*
    public byte[] rsaEncrypt(byte[] m)
    {
        return (new BigInteger(m)).modPow(e, n).toByteArray();
    }
 
    public byte[] rsaDecrypt(byte[] m)
    {
        return (new BigInteger(m)).modPow(d, n).toByteArray();
    }
   */ 
    public byte[] encrypt2(byte[] m, BigInteger e, BigInteger n)
    {
        return (new BigInteger(m).modPow(e, n).toByteArray());
    }
    
    public byte[] decrypt2(byte[] m, BigInteger d, BigInteger n)
    {
        return (new BigInteger(m)).modPow(d, n).toByteArray();
    }
}
