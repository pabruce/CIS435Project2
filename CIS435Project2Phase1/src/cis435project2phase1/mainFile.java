/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import static cis435project2phase1.ShiftCipher.decryptShiftCipher;
import static cis435project2phase1.ShiftCipher.encryptShiftCipher;
import java.util.Scanner;

/**
 *
 * @author bruce
 */
public class mainFile 
{
     public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String for Encryption: ");
        String message = new String();
        message = sc.nextLine();
        System.out.println(encryptShiftCipher(message, 3));
        System.out.println(decryptShiftCipher(encryptShiftCipher(message, 3), 3));
        sc.close();
    }
}
