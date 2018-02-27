/*
 * This test is run using a 12 letter shift
 * It tests the class, Shift Cipher against a 12 letter shift
 * The test it was used against was "Hello"
 */

/**
 *
 * @author bruce
 * @date 2/24/18
 */
import static cis435project2phase1.ShiftCipher.decryptShiftCipher;
import static cis435project2phase1.ShiftCipher.encryptShiftCipher;
import java.util.Scanner;

/**
 *
 * @author bruce
 */

public class ShiftCipherTest 
{
        public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Test String for Encryption: ");
        String message = new String();
        message = sc.nextLine();
        
        System.out.println("Step # 1 - Test Shift Cipher Encryption()");
        System.out.println("Expected Result: Tqxxa");
        System.out.println("Result: " + encryptShiftCipher(message,12));
        System.out.println("-----------------------------------------");
        System.out.println("Step # 2 - Test Decryption()");
        System.out.println("Expected Result: Hello" );
        System.out.println("Result: " + decryptShiftCipher(encryptShiftCipher(message, 12), 12));
        
        sc.close();
    }
}
