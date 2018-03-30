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
import cis435project2phase1.RSACipher;
import cis435project2phase1.KeyGen;
import cis435project2phase1.KeyPair;
        



/**
 *
 * @author bruce
 */
public class ServerCA
{
    public static void main(String[] args) throws Exception
    {
        KeyGen gen = new KeyGen();
        KeyPair serverKey = gen.GenerateKeyPair();
        RSACipher rsa = new RSACipher();
        
        
        BigInteger ServerD = serverKey.bigboy[0];
        BigInteger ServerE = serverKey.bigboy[1];
        BigInteger ServerN = serverKey.bigboy[2];
        
        ServerSocket server = new ServerSocket(8080);
        
        while(true){
        System.out.println("server online at: " + server.getInetAddress());
        Socket socket = server.accept();
        
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
        OutputStream output = socket.getOutputStream();
        PrintWriter pwrite = new PrintWriter(output,true);
        
        
        String receive;
        
        receive=input.readLine();
        if(receive.isEmpty())
        {
        
        }
        else
        {
            System.out.println("received public key");
            System.out.println(receive);
            pwrite.println(RSACipher.bytetoStringConversion(rsa.encrypt2(receive.getBytes(), ServerD, ServerN)));
            System.out.println("sent certificate");
        }
        socket.close();
        }
        

    }
    
}
