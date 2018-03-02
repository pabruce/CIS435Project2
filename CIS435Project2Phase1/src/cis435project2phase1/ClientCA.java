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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import cis435project2phase1.KeyGen;

public class ClientCA 
{
private Socket socket = null;
private ObjectInputStream inputStream = null;
private ObjectOutputStream outputStream = null;
private boolean isConnected = false;

    public ClientCA() 
    {
        
    }

    public void Transfer() 
    {

        while (!isConnected) 
        {
           try 
           {
            socket = new Socket("localHost", 9090);
            System.out.println("Connected");
            isConnected = true;
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            KeyGen keyGen = new KeyGen();
            System.out.println("Object to be written = " + keyGen);
            outputStream.writeObject(keyGen);

           }
           catch (SocketException se) 
           {
            se.printStackTrace();
            // System.exit(0);
           } 
           catch (IOException e)
           {
            e.printStackTrace();
           }
        }
    }
    
    public static void main(String[] args) 
    {
        ClientCA client = new ClientCA();
        client.Transfer();
    }
}
