/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
import cis435project2phase2.LocalCA;
import cis435project2phase2.NetworkFinal;
import cis435project2phase2.Receiver1;
import cis435project2phase2.SecureMessageSystemSimulation;
import cis435project2phase2.Sender1;
import cis435project2phase2.ShiftCipher;

public class FinalPhaseTwoTest
{
    public static void main(String[] args) throws Exception
    {
       SecureMessageSystemSimulation test = new SecureMessageSystemSimulation();
        
        test.test1();
        test.test2();
        test.test3();
        test.test4();        
    }
}
