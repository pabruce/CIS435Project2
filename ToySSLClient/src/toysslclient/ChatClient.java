package toysslclient;

import java.math.BigInteger;
import java.net.*;
import java.io.*;
import java.util.Random;

public class ChatClient 
{
    
    static final int DEFAULT_PORT = 1728;
    static final String HANDSHAKE = "CIS435/535";
    static final char MESSAGE = '0'; 
    static final char CLOSE = '1'; 
    static final int MASTER_KEY = 1234;
    
    
    static int sNonce;
    static int cNonce;
    static int length;
    static int serverSeqNum = 0;
    static int serverClientNum = 0;
    static int type;
    static int hashNum;
    
    
    static String lengthString;
    static String serverSeqNumString;
    static String clientSeqNumString;
    static String typeString;
    static String hashNumString;
    

    public static void main(String[] args) {

        String computer = "localhost";

        int port = DEFAULT_PORT;   // The port on which the server listens.

        Socket connection;      // For communication with the server.

        BufferedReader incoming;  // Stream for receiving data from server.
        DataOutputStream outgoing;     // Stream for sending data to server.
        String messageOut;        // A message to be sent to the server.
        String messageIn;         // A message received from the server.

        ObjectInputStream objectIn;
        ObjectOutputStream objectOut;
        String serverPubKeyValue;

        BufferedReader userInput; // A wrapper for System.in, for reading

        try {
            System.out.println("Connecting to " + computer + " on port " + port);
            connection = new Socket(computer,port);
            incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            outgoing = new DataOutputStream(connection.getOutputStream());
            outgoing.writeBytes(HANDSHAKE + "\r");  // Send handshake
            outgoing.flush();
            messageIn = incoming.readLine();  // Receive handshake
            if (! messageIn.equals(HANDSHAKE) ) {
                throw new IOException("Connected program is not CLChat!");
            }

            // Get Server nonce from Server
            String serverNonceString = incoming.readLine();
            sNonce = Integer.valueOf(serverNonceString);
            System.out.println("Server Nonce: " + sNonce);

            // Send Client nonce to Server
            Random random = new Random(System.currentTimeMillis());
            cNonce = random.nextInt(10) + 1;
            outgoing.writeBytes(cNonce + "\r");
            System.out.println("Client Nonce: " + cNonce);
            outgoing.flush();

            // Get public key values from server for Server's Public Key Encryption
            serverPubKeyValue = incoming.readLine();

            // Separates public key values, n and e from each other 
            String nValString = "", eValString = " ";
            for(int i = 0; i < serverPubKeyValue.length(); i++)
            {
                if(serverPubKeyValue.charAt(i) == ' ')
                {
                    eValString = serverPubKeyValue.substring(i+1);
                }
                if(serverPubKeyValue.charAt(i) == ' ') 
                {
                    break;
                }
                else
                {
                    nValString = nValString + serverPubKeyValue.charAt(i);
                }
            }

            int n = Integer.valueOf(nValString);
            
            int e = Integer.valueOf(eValString);

            RSAPublicKeyCalculator serverPublicKey = new RSAPublicKeyCalculator(41, 43);

            // Add nonces master key 

            System.out.println("Master before Nonces: " + MASTER_KEY);
            int masterKey = MASTER_KEY + sNonce + cNonce;
            System.out.println("Master with Nonces: " + masterKey);

            // Encrypt master key using Server's Public Key , n and e
            int encryptedMasterPublic;
            encryptedMasterPublic = serverPublicKey.encryptPublicKey(n, e, masterKey);
            System.out.println("Encrypted Master Public: " + encryptedMasterPublic);

            // Send encrypted master key to server 
            outgoing.writeBytes(encryptedMasterPublic + "\r");
            outgoing.flush();

            System.out.println("Connected.  Enter your first message.");
        }
        catch (Exception e) 
        {
            System.out.println("An error occurred while opening connection.");
            System.out.println(e.toString());
            return;
        }

        try 
        {
            // Start exchanging of messages.
            userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("NOTE: Enter 'quit' to end the program.\n");
            while (true) {
                System.out.print("SEND:      ");
                messageOut = userInput.readLine();

                if (messageOut.equals("quit"))  {
                    // User wants to quit.  Inform the other side
                    // of the connection, then close the connection.
                    type = 1;
                    typeString = "1";
                    int Mc = generateClientMACKey(MASTER_KEY, cNonce, sNonce);
                    System.out.println("Mc: " + Mc);
                    String hashNum = hashEncryptMAC(serverClientNum, type, Mc, messageOut);
                    System.out.println("hashNum: " + hashNum);
                    String message = stringConvert(messageOut);

                    length = message.length();

                    String messageBlock = message + hashNum;
                    System.out.println("message + Hash: " + messageBlock);
                    
                    String encryptedMessage = clientEncryptionKey(messageBlock);
                    System.out.println("Encrypted messageBeforeType+Length: " + encryptedMessage);
                    
                    String typeString = String.valueOf(type).toString();
                    
                    String lengthString;
                    if(length < 10)
                    {
                        lengthString = "0" + String.valueOf(length).toString();
                    }
                    else
                    {
                        lengthString = String.valueOf(length).toString();
                    }
                    encryptedMessage = typeString + lengthString + serverClientNum + encryptedMessage;

                    System.out.println("Encrypted message After: " + encryptedMessage);

                    outgoing.writeBytes(encryptedMessage + "\r");
                    outgoing.flush();

                    serverClientNum++;

                    connection.close();
                    System.out.println("Connection closed.");
                    break;
                }

                System.out.println("Master Key: " + MASTER_KEY);
                type = 0;
                int Mc = generateClientMACKey(MASTER_KEY, cNonce, sNonce);
                System.out.println("Mc: " + Mc);
                String hashNum = hashEncryptMAC(serverClientNum, type, Mc, messageOut);
                System.out.println("Hash: " + hashNum);
                String message = stringConvert(messageOut);

                length = message.length();

                String messageBlock = message + hashNum;
                System.out.println("Message: " + message);
                System.out.println("Message + Hash: " + messageBlock);
                System.out.println("Sequence Number (Client): " + serverClientNum);
                String encryptedMessage = clientEncryptionKey(messageBlock);
                System.out.println("Encrypted message Before (Type + Length + Seq) added: " + encryptedMessage);
                String typeString = String.valueOf(type);
                String lengthString;
                if(length < 10){
                    lengthString = "0" + String.valueOf(length);
                }else{
                    lengthString = String.valueOf(length);
                }
                encryptedMessage = typeString + lengthString + serverClientNum + encryptedMessage;

                System.out.println("Encrypted message after (Type + Length + Seq) added: " + encryptedMessage);

                outgoing.writeBytes(encryptedMessage + "\r");
                outgoing.flush();

                serverClientNum++;

                System.out.println("WAITING...");
                messageIn = incoming.readLine();
                if (messageIn.length() > 0) {
                    if (messageIn.charAt(0) == CLOSE) 
                    {
                        System.out.println("Connection closed at other end.");
                        connection.close();
                        break;
                    }
                }
                typeString = messageIn.substring(0, 1);
                System.out.println("Type (Server): " + typeString);
                int serverType = Integer.valueOf(typeString);
                lengthString = messageIn.substring(1, 3);
                length = Integer.valueOf(lengthString);
                System.out.println("Length (Server): " + lengthString);
                clientSeqNumString = messageIn.substring(3,4);
                System.out.println("Sequence (Server): " + clientSeqNumString);

                String messageEncrypted = messageIn.substring(4);
                System.out.println("Encrypted Message: " + messageEncrypted);
                String messageDecrypted = decryptServer(messageEncrypted);
                System.out.println("Decrypted Message: " + messageDecrypted);

                hashNumString = messageDecrypted.substring(messageDecrypted.length() - 1);

                int Ms = generateServerMACKey(MASTER_KEY, cNonce, sNonce);
                String hashNumStringCmp = hashDecryptMAC(serverSeqNum, serverType, Ms, messageDecrypted.substring(0, messageDecrypted.length() - 1));

                System.out.println("hash: " + hashNumString);
                System.out.println("hashStringCmp: " + hashNumStringCmp);
                if(!hashNumString.equals(hashNumStringCmp)){
                    throw new Exception("Macs don't match!");
                }

                String messageBefore = messageDecrypted.substring(0, messageDecrypted.length() - 1);
                System.out.println("messageBefore: " + messageBefore);

                String messageFinal = stringConvertBack(messageBefore);
                System.out.println("RECEIVED:  " + messageFinal);
            }
        }
        catch (Exception e) {
            System.out.println("Sorry, an error has occurred.  Connection lost.");
            System.out.println(e.toString());
            System.exit(1);
        }
    }  // end main()

    public static String clientEncryptionKey(String message){
        BigInteger bigIntMessage = new BigInteger(message);
        BigInteger bigIntMaster = BigInteger.valueOf(MASTER_KEY);
        BigInteger bigCNonce = BigInteger.valueOf(cNonce);
        BigInteger bigSNonce = BigInteger.valueOf(sNonce);

        bigIntMaster = bigIntMaster.subtract(bigCNonce);
        bigIntMaster = bigIntMaster.add(bigSNonce);
        System.out.println("CNonce: " + bigCNonce);
        System.out.println("SNonce: " + bigSNonce);
        System.out.println("Master after Nonces: " + bigIntMaster);

        bigIntMessage = bigIntMessage.add(bigIntMaster);
        String result;
        result = String.valueOf(bigIntMessage);
        if(result.length() % 2 == 0){
            result = "0" + result;
        }
        return result;
    }
    public static int generateClientMACKey(int masterKey, int clientNonce, int serverNonce){
        int result = 0;
        result = masterKey + clientNonce + serverNonce;
        return result;
    }
    public static int generateServerMACKey(int masterKey, int clientNonce, int serverNonce){
        int result = 0;
        result = masterKey - clientNonce - serverNonce;
        return result;
    }
    public static String hashEncryptMAC(int seq, int type, int mc, String message){
        String result = "";
        int number = 5;
        String m = stringConvert(message);
        BigInteger bigRand = BigInteger.valueOf(number);
        BigInteger big =  new BigInteger(seq + type + mc + m);
        big = big.mod(bigRand);
        result = String.valueOf(big);
        return result;
    }
    public static String hashDecryptMAC(int seq, int type, int mc, String message){
        String result = "";
        int number = 5;
        BigInteger bigRand = BigInteger.valueOf(number);
        BigInteger big =  new BigInteger(seq + type + mc + message);
        big = big.mod(bigRand);
        result = String.valueOf(big);
        return result;
    }
    public static String stringConvert(String message) {
        String result = "";
        for(int i = 0; i < message.length(); i++) {
            result += convertChartoNum(message.charAt(i));
        }
        return result;
    }
    public static String convertChartoNum(char c) {
        String result = "";
        switch (c) {
            case 'a':
                result = "01";
                break;
            case 'b':
                result = "02";
                break;
            case 'c':
                result = "03";
                break;
            case 'd':
                result = "04";
                break;
            case 'e':
                result = "05";
                break;
            case 'f':
                result = "06";
                break;
            case 'g':
                result = "07";
                break;
            case 'h':
                result = "08";
                break;
            case 'i':
                result = "09";
                break;
            case 'j':
                result = "10";
                break;
            case 'k':
                result = "11";
                break;
            case 'l':
                result = "12";
                break;
            case 'm':
                result = "13";
                break;
            case 'n':
                result = "14";
                break;
            case 'o':
                result = "15";
                break;
            case 'p':
                result = "16";
                break;
            case 'q':
                result = "17";
                break;
            case 'r':
                result = "18";
                break;
            case 's':
                result = "19";
                break;
            case 't':
                result = "20";
                break;
            case 'u':
                result = "21";
                break;
            case 'v':
                result = "22";
                break;
            case 'w':
                result = "23";
                break;
            case 'x':
                result = "24";
                break;
            case 'y':
                result = "25";
                break;
            case 'z':
                result = "26";
                break;
            case ' ':
                result = "27";
                break;
            default:
                break;
        }

        return result;













    }
    public static String convertNumToChar(String c) {
        String result = "";
        switch (c) {
            case "01":
                result = "a";
                break;
            case "02":
                result = "b";
                break;
            case "03":
                result = "c";
                break;
            case "04":
                result = "d";
                break;
            case "05":
                result = "e";
                break;
            case "06":
                result = "f";
                break;
            case "07":
                result = "g";
                break;
            case "08":
                result = "h";
                break;
            case "09":
                result = "i";
                break;
            case "10":
                result = "j";
                break;
            case "11":
                result = "k";
                break;
            case "12":
                result = "l";
                break;
            case "13":
                result = "m";
                break;
            case "14":
                result = "n";
                break;
            case "15":
                result = "o";
                break;
            case "16":
                result = "p";
                break;
            case "17":
                result = "q";
                break;
            case "18":
                result = "r";
                break;
            case "19":
                result = "s";
                break;
            case "20":
                result = "t";
                break;
            case "21":
                result = "u";
                break;
            case "22":
                result = "v";
                break;
            case "23":
                result = "w";
                break;
            case "24":
                result = "x";
                break;
            case "25":
                result = "y";
                break;
            case "26":
                result = "z";
                break;
            case "27":
                result = " ";
                break;
            default:
                break;
        }
        return result;
    }
    public static String stringConvertBack(String message) {
        String result = "";
        String temp = "";
        for(int i = 0; i < message.length()/2; i++) {
            temp = message.substring(2 * i, 2 * i + 2);
            result = result + convertNumToChar(temp);
        }
        return result;
    }
    public static String decryptServer(String messageEncrypted)
    {
        String result;
        BigInteger bigMessage = new BigInteger(messageEncrypted);
        BigInteger masterSecret = BigInteger.valueOf(MASTER_KEY);
        BigInteger bigCNonce = BigInteger.valueOf(cNonce);
        BigInteger bigSNonce = BigInteger.valueOf(sNonce);

        masterSecret = masterSecret.add(bigSNonce);
        masterSecret = masterSecret.subtract(bigCNonce);
        bigMessage = bigMessage.subtract(masterSecret);

        System.out.println("CNonce: " + bigCNonce);
        System.out.println("SNonce: " + bigSNonce);
        System.out.println("masterSecret: " + masterSecret);

        result = String.valueOf(bigMessage);
        if(result.length() % 2 == 0){
            result = "0" + result;
        }
        return result;
    }
    }
 //end class ChatClient