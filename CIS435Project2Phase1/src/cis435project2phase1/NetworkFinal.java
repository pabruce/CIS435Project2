/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;
/**
 *
 * @author Patrick
 */

import cis435project2phase1.*;

public class NetworkFinal
{
    byte[] senderPacket;
    byte[] receiverPacket;
    
    NetworkFinal()
    {
        System.out.println("Created Network");
    }
    
    public void sendPacketToReceiver(byte[] encrypted)
    {
        senderPacket = encrypted;
    }
    
    public byte[] receiveSenderPacket()
    {
        return receiverPacket;
    }
}
