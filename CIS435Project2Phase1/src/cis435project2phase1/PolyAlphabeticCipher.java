/**
 * This class handles an input from the user
 * It runs the input by letter and returns an encryption/decryption
 * It can handle Lowercase, Uppercase and Spaces
 * However, it returns the decrypted text as uppercase
 */
package cis435project2phase1;

/**
 *
 * @author Patrick Bruce
 * @date   2/20/18
 */

public class PolyAlphabeticCipher
{
    public static String polyEncrypt(String text, final String key)
    {
        String cipherText = "";
        text = text.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            cipherText += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return cipherText;
    }
    
     public static String polyDecrypt(String text, final String key)
    {
        String cipherText = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            cipherText += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return cipherText;
    }
}
