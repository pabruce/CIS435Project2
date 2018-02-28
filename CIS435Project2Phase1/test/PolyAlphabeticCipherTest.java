/*
 * This test is run using a polyalphabetic cipher shift 
 * I used the message "Hello World" and run it against the cipher
 * The output converts the message into capital letters and removes spaces. 
 */
import static cis435project2phase1.PolyAlphabeticCipher.polyDecrypt;
import static cis435project2phase1.PolyAlphabeticCipher.polyEncrypt;
/**
 * @author Patrick Bruce
 * @date 2/24/18
 */
public class PolyAlphabeticCipherTest 
{
    public static void main(String[] args)
    {
        System.out.println("Step # 1 - Test PolyAlphabetic Encryption()");
        String key = "POLYALPHABETICCIPHER";
        String message = "Hello World";
        String encryptedMsg = polyEncrypt(message, key);
        System.out.println("String: " + message);
        System.out.println("Encrypted message: " + encryptedMsg);
        System.out.println("Expected Decrypted message: HELLOWORLD");
        System.out.println("Decrypted message: " + polyDecrypt(encryptedMsg, key));
    }
    
}
