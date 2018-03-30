/*
 * The test takes a message string 'hello'
 * It takes user input as a command, however it was using a test case 'hello'
 * The message it encrypts is a custom alphabet using 'qzuuc' as its encryption
 */

/**
 *
 * @author Patrick Bruce
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
        System.out.println("Step # 1 - Test Substitution Cipher Encryption()");
        System.out.println("Result: " + en);
        
        System.out.println("Step # 2 - Test Substitution Cipher Decryption()");
        System.out.println("Expected Result: hello");
        System.out.println("Result: " + subDecrypt(en));
        sc.close();
    }
}
