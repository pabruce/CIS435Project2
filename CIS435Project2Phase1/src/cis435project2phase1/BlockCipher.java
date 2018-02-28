/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

/**
 *
 * @author bruce
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlockCipher
{
    private byte[] key;

    private static final String BlockAES = "AES";

    //using AES to encrypt / decrypt block cipher to base 64 
    public BlockCipher(byte[] key)
    {
        this.key = key;
    }

    //takes plain text to encrypt
    public byte[] blockEncrypt(byte[] plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, BlockAES);
        Cipher cipher = Cipher.getInstance(BlockAES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    /**
     * Decrypts the given byte array
     *
     * @param cipherText The data to decrypt
     */
    public byte[] blockDecrypt(byte[] cipherText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, BlockAES);
        Cipher cipher = Cipher.getInstance(BlockAES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}