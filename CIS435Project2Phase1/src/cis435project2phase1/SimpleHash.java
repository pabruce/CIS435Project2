/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

/**
 *
 * @author Forrest
 */
public class SimpleHash {
    
    public byte hash(byte[] input)
    {
        return input[input.length];     //while a terrible hash, and not at all secure, it is technically still a hash
    }
    
}
