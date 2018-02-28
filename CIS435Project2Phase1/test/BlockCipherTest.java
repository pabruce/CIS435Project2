/*
 * The test uses an encryption key I hand generated. 
 * The plaintext is converted using the standard characterset to Bytes 
 * AES is then used to run the encryption of the plain text 
 * The test uses 'Hello' and encryption as well as decrypts the message
 */

/**
 *
 * @author Patrick Bruce
 * @date 2/27/18
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
        System.out.println("Test Block Cipher Encryption() message: " +new String(cipherText));
        System.out.println("Expected Decrypted message: Hello World");
        System.out.println("Test Block Cipher Decryption() message: "  +new String(decryptedCipherText));
        
     }
}
