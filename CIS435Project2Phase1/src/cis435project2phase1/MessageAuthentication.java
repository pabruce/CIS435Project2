/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageAuthentication {

   public static String getPlainText() {
      System.out.print("Enter plaintext:");
      String plaintext = " ";
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try
      {
         plaintext = br.readLine();
      } 
      catch (IOException error) 
      {
         System.out.println("Error");
         System.exit(1);
      }    
      return plaintext;
   }       

}          