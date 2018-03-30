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
import java.util.Queue;
import java.util.LinkedList;

public class NetworkFinal
{
    byte[] channel;
    
    public NetworkFinal()
    {
        System.out.println("Created Network");
    }
    
    public void getPacketFromSender(byte[] input)
    {
        channel = input;
    }
    
    public byte[] deliverPacketToReceiver()
    {
        return channel;
    }
    
    public void packetGetHacked()
    {
        for(int i = 30; i < channel.length-100; i++)
        {
            channel[i] = (byte) 0x2f;
        }
    }
}
