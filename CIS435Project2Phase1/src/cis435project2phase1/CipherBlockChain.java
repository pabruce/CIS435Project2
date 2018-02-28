/*
 * Block chain generates an IV to use then hashes the key
 * it encrypts the key and then concatenates the text with the IV 
 * the result is then the encrypted IV and key with the text 
 */
package cis435project2phase1;

/**
 *
 * @author Patrick Bruce
 * @date 2/27/18
 */

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class CipherBlockChain 
{
    public static byte[] cipherChainEncrypt(String plainText, String key) throws Exception {
        byte[] plain = plainText.getBytes();

        // Generate Initivialization Vector
        int initvecSize = 16;
        byte[] initVec = new byte[initvecSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initVec);
        IvParameterSpec initvecParameterSpec = new IvParameterSpec(initVec);

        // Hash key
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //Used a library padding
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, initvecParameterSpec);
        byte[] encrypted = cipher.doFinal(plain);

        // Concatenated Initialization vector and text
        byte[] encrIVAndText = new byte[initvecSize + encrypted.length];
        System.arraycopy(initVec, 0, encrIVAndText, 0, initvecSize);
        System.arraycopy(encrypted, 0, encrIVAndText, initvecSize, encrypted.length);

        return encrIVAndText;
    }

    public static String cipherChainDecrypt(byte[] encryptedIVText, String key) throws Exception {
        int initvecSize = 16;
        int keySize = 16;

        //Extract the initialization vector
        byte[] initVec = new byte[initvecSize];
        System.arraycopy(encryptedIVText, 0, initVec, 0, initVec.length);
        IvParameterSpec initvecParameterSpec = new IvParameterSpec(initVec);

        //Extract the encryption
        int encryptedSize = encryptedIVText.length - initvecSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIVText, initvecSize, encryptedBytes, 0, encryptedSize);

        //Hash
        byte[] keyBytes = new byte[keySize];
        MessageDigest mesDig = MessageDigest.getInstance("SHA-256");
        mesDig.update(key.getBytes());
        System.arraycopy(mesDig.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        //Decrypt
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, initvecParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}
