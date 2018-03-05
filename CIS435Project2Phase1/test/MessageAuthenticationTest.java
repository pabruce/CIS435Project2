
import static cis435project2phase1.MessageAuthentication.getPlainText;
import static cis435project2phase1.MessageAuthentication.*;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dghelardini
 */
public class MessageAuthenticationTest {
    public static void main(String[] args) throws Exception {
      System.out.println("This program generates a message authentication code for the plaintext you enter.");
      String plaintextString = getPlainText();
      byte[] plaintext = plaintextString.getBytes();

      KeyGenerator keygen = KeyGenerator.getInstance("HmacMD5");
      SecretKey sKey = keygen.generateKey();

      Mac theMac = Mac.getInstance("HmacMD5");
      theMac.init(sKey);

      byte[] theMacCode = theMac.doFinal(plaintext);

      System.out.print("The MAC for the plaintext \'" +
                       plaintextString + "\' is ");
      for (int i = 0; i < theMacCode.length; i++) {
      System.out.print(theMacCode[i]);
      if (i != theMacCode.length - 1) {
         System.out.print(",");
      }    // if
   }       // for i
   System.out.println();
   }       // main

    
}
