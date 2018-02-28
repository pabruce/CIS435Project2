/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
import cis435project2phase1.BlockCipher;
import cis435project2phase1.BlockCipher.*;
import java.nio.charset.StandardCharsets;

public class BlockCipherTest 
{
     public static void main(String[] args) throws Exception
     {
        byte[] encryptionKey = "MZytUvNdHCpFroLb".getBytes(StandardCharsets.UTF_8);
        byte[] plainText = "Hello World".getBytes(StandardCharsets.UTF_8);
        BlockCipher advEncrStndrd = new BlockCipher(encryptionKey);
        byte[] cipherText = advEncrStndrd.blockEncrypt(plainText);
        byte[] decryptedCipherText = advEncrStndrd.blockDecrypt(cipherText);

        System.out.println("Plain Text message: " +new String(plainText));
        System.out.println("Encrypted Block Cipher message: " +new String(cipherText));
        System.out.println("Expected Decrypted message: Hello World");
        System.out.println("Decrypted message: " +new String(decryptedCipherText));
        
     }
}
