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
public class SubstitutionCipher 
{
    public static char oA[]  = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    
    public static char eA[] = { 'e','w','y','b','z','d','j','q','r','t','n','u','a','h','c','i','p','m','f',
        'x','l','s','o','g','k','v'};
    
    public static String subEncrypt(String m)
    {
        char eAEncrypter[] = new char[(m.length())];
        for (int i = 0; i < m.length(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (oA[j] == m.charAt(i))
                {
                    eAEncrypter[i] = eA[j];
                    break;
                }
            }
        }
        
        return (new String(eAEncrypter));
    }
    
    public static String subDecrypt(String m)
    {
        char oADecrypter[] = new char[(m.length())];
        for (int i = 0; i < m.length(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (oA[j] == m.charAt(i))
                {
                    oADecrypter[i] = oA[j];
                    break;
                }
            }
        }
        
        return (new String(oADecrypter));
    }
}
