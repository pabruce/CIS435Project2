/*
 * This class uses a key generator 
 * With an array of bytes to generate the encryption
 * it runs the generated key through Advanced Encryption Standard 
 */
package cis435project2phase1;

/**
 *
 * @author Patrick Bruce
 * @date 2/27/18
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlockCipher
{
    private byte[] key;

    private static final String BlockAES = "AES";

    //using AES to encrypt / decrypt block cipher
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


     //Decrypts the given byte array
    public byte[] blockDecrypt(byte[] cipherText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, BlockAES);
        Cipher cipher = Cipher.getInstance(BlockAES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}