/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

/**
 *
 * @author anthonyweber
 */
import java.math.BigInteger;
import cis435project2phase1.BlockCipher;
import cis435project2phase1.DigitalSignature;


public class Final
{
    public byte[] encrypt(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderD, BigInteger ReceiverE, byte[] symetricKey, byte[] message) throws Exception
    {
        byte[] symMessage;
        byte[] aSymMessage;
        BlockCipher blockCipher = new BlockCipher(symetricKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = blockCipher.blockEncrypt(message);
        
        aSymMessage = digSig.encrypt(SenderN, ReceiverN, SenderD, ReceiverE, symMessage);
        
        return aSymMessage;        
        
    }
    
    public byte[] decrypt(BigInteger SenderN, BigInteger ReceiverN, BigInteger SenderE, BigInteger ReceiverD, byte[] symetricKey, byte[] message) throws Exception
    {
        byte[] symMessage;
        byte[] plaintext;
        BlockCipher blockCipher = new BlockCipher(symetricKey);
        DigitalSignature digSig = new DigitalSignature();
        
        symMessage = digSig.decrypt(SenderN, ReceiverN, SenderE, ReceiverD, message);
        
        plaintext = blockCipher.blockDecrypt(symMessage);
        
        return plaintext;        
    }
    
    
//    
//    
//    
//    private BigInteger p,q,n,e,d,ph;
//    private Random rand;
//    private int bitlength = 1024;
//    private byte[] key;
//    private static final String BlockAES = "AES";
//
//    public Final()
//    {
//        rand = new Random();
//        p = BigInteger.probablePrime(bitlength, rand);
//        q = BigInteger.probablePrime(bitlength, rand);
//        n = p.multiply(q);
//        ph = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//        e = BigInteger.probablePrime(bitlength / 2,rand);
//        
//        while (ph.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(ph) < 0)
//        {
//            e.add(BigInteger.ONE);
//        }
//        d = e.modInverse(ph);
//    }
//    
//    public Final(BigInteger e,BigInteger d, BigInteger n)
//    {
//        this.e = e;
//        this.d = d;
//        this.n = n;
//    }
//    public Final(byte[] key)
//    {
//        this.key = key;
//    }
//    public byte[] blockEncrypt(byte[] plainText) throws Exception
//    {
//        SecretKeySpec secretKey = new SecretKeySpec(key, BlockAES);
//        Cipher cipher = Cipher.getInstance(BlockAES);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//        return cipher.doFinal(plainText);
//    }
//     public static String bytetoStringConversion(byte[] encrypted)
//    {
//        String test = "";
//        for (byte b : encrypted)
//        {
//            test += Byte.toString(b);
//        }
//        return test;
//    }
// 
//    public byte[] encrypt(byte[] m)
//    {
//        return (new BigInteger(m)).modPow(e, n).toByteArray();
//    }
// 
//    public byte[] decrypt(byte[] m)
//    {
//        return (new BigInteger(m)).modPow(d, n).toByteArray();
//    }
//      public byte[] blockDecrypt(byte[] cipherText) throws Exception
//    {
//        SecretKeySpec secretKey = new SecretKeySpec(key, BlockAES);
//        Cipher cipher = Cipher.getInstance(BlockAES);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//        return cipher.doFinal(cipherText);
//    }
}

