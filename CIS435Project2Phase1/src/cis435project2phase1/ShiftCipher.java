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
public class ShiftCipher 
{
    public static final String ALPHABET_LOWCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHABET_UPPCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    //encrypt checks for both upper and lower case
    public static String encryptShiftCipher(String plainText, int shiftKey)
    {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            int charPosition = -1;
            char replaceVal;
            int keyVal = -1;
            char val = plainText.charAt(i);
            System.out.println(val);
            //checks here for upper case letters
            if(Character.isUpperCase(val))
            {
                charPosition = ALPHABET_UPPCASE.indexOf(val);
                if(charPosition != -1)
                {
                    keyVal = (shiftKey + charPosition) % 26;
                    replaceVal = ALPHABET_UPPCASE.charAt(keyVal);
                } 
                else 
                {
                    replaceVal = plainText.charAt(i);
                }           
            } 
            //checks for lower case from user input
            else
            {
                charPosition = ALPHABET_LOWCASE.indexOf(val);
                if(charPosition != -1) 
                {
                    keyVal = (shiftKey + charPosition) % 26;
                    replaceVal = ALPHABET_LOWCASE.charAt(keyVal);
                } else 
                {
                    replaceVal = plainText.charAt(i);
                }
            }       
            //outputs encrypted cipher text, value for cipher shift applied in main 
            //System.out.println("Encrypted Text:" + cipherText);
            cipherText += replaceVal;        
        }
        return cipherText;
    }

    public static String decryptShiftCipher(String cipherText, int shiftKey)
    {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            int charPosition = -1;
            char replaceVal;
            int keyVal = -1;
            char val = cipherText.charAt(i);

            if(Character.isUpperCase(val)) 
            {
                charPosition = ALPHABET_UPPCASE.indexOf(val);
                if(charPosition != -1) 
                {
                    keyVal = (charPosition - shiftKey) % 26;
                    if (keyVal < 0) 
                    {
                        keyVal = ALPHABET_UPPCASE.length() + keyVal;
                    }
                    replaceVal = ALPHABET_UPPCASE.charAt(keyVal);
                } else 
                {
                    replaceVal = cipherText.charAt(i);
                }           
            } 
            else
            {
                charPosition = ALPHABET_LOWCASE.indexOf(val);
                if(charPosition != -1) 
                {
                    keyVal = (charPosition - shiftKey) % 26;
                    if (keyVal < 0) 
                    {
                        keyVal = ALPHABET_LOWCASE.length() + keyVal;
                    }
                    replaceVal = ALPHABET_LOWCASE.charAt(keyVal);
                } 
                else 
                {
                    replaceVal = cipherText.charAt(i);
                }
            }
            //System.out.println("Decrypted Text:" + plainText);
            plainText += replaceVal;
        }
        return plainText;
    }
}
