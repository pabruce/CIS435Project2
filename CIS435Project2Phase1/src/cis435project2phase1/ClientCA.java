/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

/**
 *
 * @author bruce
 */

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import cis435project2phase1.KeyGen;
import java.io.BufferedReader;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
import java.math.BigInteger;

public class ClientCA 
{

    public static void main(String[] args) throws Exception 
    {
        Socket socket = new Socket("127.0.0.1", 8080);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
        OutputStream output = socket.getOutputStream();
        PrintWriter pwrite = new PrintWriter(output,true);
        
        KeyGen gen = new KeyGen();
        KeyPair clientKey = gen.GenerateKeyPair();
        
        BigInteger ClientD = clientKey.bigboy[0];
        BigInteger ClientE = clientKey.bigboy[1];
        BigInteger ClientN = clientKey.bigboy[2];
        
        pwrite.println(ClientE.toString() + ClientN.toString());
        
        String receive;
        receive=input.readLine();
        if(receive.isEmpty())
        {
        
        }
        else
        {
            System.out.println("received certificate");
            System.out.println(receive);
        
        }
        socket.close();
        
    }

    
}
