/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis435project2phase1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.math.BigInteger;
import cis435project2phase1.KeyGen;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bruce
 */
public class ServerCA
{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    
    public ServerCA()
    {}

    public void Transfer() throws IOException
    {
        try
        {
            serverSocket = new ServerSocket(9090);
            socket = serverSocket.accept();
            System.out.println("Connected");
            inStream = new ObjectInputStream(socket.getInputStream());
            
            KeyGen keyGen = (KeyGen) inStream.readObject();
            System.out.println("Keys Generated " + keyGen);
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(ServerCA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        ServerCA CA = new ServerCA();
        CA.Transfer();
    }
}
