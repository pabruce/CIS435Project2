/*
 * The test takes a message string 'hello'
 * It takes user input as a command, however it was using a test case 'hello'
 * 
 */

/**
 *
 * @author bruce
 * @date 2/25/18
 */

import static cis435project2phase1.SubstitutionCipher.subEncrypt;
import static cis435project2phase1.SubstitutionCipher.subDecrypt;
import java.util.Scanner;

public class SubstitutionCipherTest 
{
     public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String en = subEncrypt(sc.next().toLowerCase());
        System.out.println("Encrypted message: " + en);
        System.out.println("Expected Decrypted message: hello");
        System.out.println("Decrypted message: " + subDecrypt(en));
        sc.close();
    }
}
