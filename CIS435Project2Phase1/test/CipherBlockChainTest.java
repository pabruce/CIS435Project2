/*
 * This test takes a key, 'thisisonlyatest' as the symmetric key and tests CBC
 * The plain text it converts is 'Hello World'
 */

/**
 *
 * @author Patrick Bruce
 * @date 2/27/18
 */

import static cis435project2phase1.CipherBlockChain.cipherChainDecrypt;
import static cis435project2phase1.CipherBlockChain.cipherChainEncrypt;

public class CipherBlockChainTest 
{
       public static void main(String[] args) throws Exception 
    {
        String key = "thisisonlyatest";
        String plainText = "Hello World";
        byte[] encrypted = cipherChainEncrypt(plainText, key);
        System.out.println("Test Cipher Block Chain Encryption(): " + cipherChainEncrypt(plainText,key));
        String decrypted = cipherChainDecrypt(encrypted, key);
        System.out.println("Expected Decryption : Hello World");
        System.out.println("Test Cipher Block Chain Decryption(): " + cipherChainDecrypt(encrypted,key));
    }
   
    
}
