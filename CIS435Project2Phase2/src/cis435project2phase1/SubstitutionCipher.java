/*
 * Wrote 2 alphabets, an original and encrypted
 * The encryption takes the string and compares a counter of characters for the msg
 * Then, it takes those characters, finds their equivalent in the alphabet substitute
 */
package cis435project2phase1;

/**
 *
 * @author Patrick Bruce
 * @date 2/25/18
 */
public class SubstitutionCipher 
{
    public static char oA[]  = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    
    public static char eA[] = { 'e','w','y','b','z','d','j','q','r','t','n','u','a','h','c','i','p','m','f',
        'x','l','s','o','g','k','v'};
    
    public static String subEncrypt(String m)
    {
        char cEncrypt[] = new char[(m.length())];
        for (int i = 0; i < m.length(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                if (oA[j] == m.charAt(i))
                {
                    cEncrypt[i] = eA[j];
                    break;
                }
            }
        }
        return (new String(cEncrypt));
    }
    
    public static String subDecrypt(String m)
    {
        char cDecrypt[] = new char[(m.length())];
        
        for (int l = 0; l < m.length(); l++)
        {
            for (int n = 0; n < 26; n++)
            {
                if (eA[n] == m.charAt(l))
                {
                    cDecrypt[l] = oA[n];
                    break;
                }
            }
        }
        
        return (new String(cDecrypt));
    }
}
