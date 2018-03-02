/**
 * This class handles an input from the user
 * It runs the input by letter and returns an encryption/decryption
 * It can handle Lowercase, Uppercase and Spaces
 */
package cis435project2phase1;
/**
 *
 * @author Patrick Bruce
 * @date   2/20/18
 */
public class ShiftCipher 
{
    public static final String ALPHABET_LOWCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHABET_UPPCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    //encrypt checks for both upper and lower case
    public static String encryptShiftCipher(String plainText, int key)
    {
        String cipherText = "";
        
        for (int i = 0; i < plainText.length(); i++)
        {
            int charPosition = -1;
            char replaceValue;
            int keyVal = -1;
            char val = plainText.charAt(i);
            System.out.println(val);
            //checks here for upper case letters
            if(Character.isUpperCase(val))
            {
                charPosition = ALPHABET_UPPCASE.indexOf(val);
                if(charPosition != -1)
                {
                    keyVal = (key + charPosition) % 26;
                    replaceValue = ALPHABET_UPPCASE.charAt(keyVal);
                } 
                else 
                {
                    replaceValue = plainText.charAt(i);
                }   
             
            } 
            //checks for lower case from user input
            else
            {
                charPosition = ALPHABET_LOWCASE.indexOf(val);
                if(charPosition != -1) 
                {
                    keyVal = (key + charPosition) % 26;
                    replaceValue = ALPHABET_LOWCASE.charAt(keyVal);
                } else 
                {
                    replaceValue = plainText.charAt(i);
                }
            }       
            //outputs encrypted cipher text, value for cipher shift applied in main 
            //System.out.println("Encrypted Text:" + cipherText);
            cipherText += replaceValue;        
        }
        return cipherText;
    }

    public static String decryptShiftCipher(String cipherText, int shiftKey)
    {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            int charPosition = -1;
            char replaceValue;
            int keyValue = -1;
            char cValue = cipherText.charAt(i);

            if(Character.isUpperCase(cValue)) 
            {
                charPosition = ALPHABET_UPPCASE.indexOf(cValue);
                if(charPosition != -1) 
                {
                    keyValue = (charPosition - shiftKey) % 26;
                    if (keyValue < 0) 
                    {
                        keyValue = ALPHABET_UPPCASE.length() + keyValue;
                    }
                    replaceValue = ALPHABET_UPPCASE.charAt(keyValue);
                } else 
                {
                    replaceValue = cipherText.charAt(i);
                }           
            } 
            else
            {
                charPosition = ALPHABET_LOWCASE.indexOf(cValue);
                if(charPosition != -1) 
                {
                    keyValue = (charPosition - shiftKey) % 26;
                    if (keyValue < 0) 
                    {
                        keyValue = ALPHABET_LOWCASE.length() + keyValue;
                    }
                    replaceValue = ALPHABET_LOWCASE.charAt(keyValue);
                } 
                else 
                {
                    replaceValue = cipherText.charAt(i);
                }
            }
            //System.out.println("Decrypted Text:" + plainText);
            plainText += replaceValue;
        }
        return plainText;
    }
}
