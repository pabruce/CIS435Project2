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
    Queue<byte[]> channel = new LinkedList<>();
    
    NetworkFinal()
    {
        System.out.println("Created Network");
    }
    
    public void sendPacketToReceiver(byte[] input)
    {
        channel.add(input);
    }
    
    public byte[] receiveSenderPacket()
    {
        return channel.poll();
    }
}
