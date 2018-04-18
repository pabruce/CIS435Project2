package toysslserver;

import java.math.BigInteger;
import java.net.*;
import java.io.*;
import java.util.Random;

public class ChatServer 
{
    static final int DEFAULT_PORT = 1728;
    static final String HANDSHAKE = "CIS435535";
    static final char MESSAGE = '0';
    static final char CLOSE = '1'; 
    static final int MASTER_KEY = 1234;
    
    static int sNonce;
    static int cNonce;
    static int length ;
    static int serverSeqNum = 0;
    static int clientSeqNum = 0;
    static int type;
    static int hashNum;
    
    static String lengthString;
    static String serverSeqNumString;
    static String clientSeqNumString;
    static String typeString;
    static String hashNumString;

    public static void main(String[] args) 
    {
        int p, q, n;
        int z, e, d;
        
        int port = DEFAULT_PORT;   // The port on which the server listens.
        

        ServerSocket listener;  // Listens for a connection request.
        Socket connection;      // For communication with the client.

        BufferedReader incoming;  // Stream for receiving data from client.
        DataOutputStream outgoing;     // Stream for sending data to client.
        String messageOut;        // A message to be sent to the client.
        String messageIn;         // A message received from the client.

        ObjectInputStream objectIn;
        ObjectOutputStream objectOut;

        BufferedReader userInput; // A wrapper for System.in, for reading

        try {
            listener = new ServerSocket(port);
            System.out.println("Listening on port " + listener.getLocalPort());
            connection = listener.accept();
            listener.close();
            incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()) );
            outgoing = new DataOutputStream(connection.getOutputStream());
            System.out.println("Send hanshake data to client");
            outgoing.writeBytes(HANDSHAKE + "\r");  // Send handshake to client.
            outgoing.flush();
            
            System.out.println("Receive Handshake Data From Client");
            messageIn = incoming.readLine();  // Receive handshake from client.
            if (! HANDSHAKE.equals(messageIn) ) {
                throw new Exception("Connected program is not a ChatClient!");
            }

           //set the public and private keys of server
            RSAPublicKeyCalculator publicKey = new RSAPublicKeyCalculator(41, 43);
            RSAPrivateKeyCalculator privateKey = new RSAPrivateKeyCalculator(41, 43);

            // Send server nonce to client
            Random random = new Random(System.currentTimeMillis());
            sNonce = random.nextInt(10) + 1;
            System.out.println("Generate Server Nonce: " + sNonce);
            outgoing.writeBytes(sNonce + "\r");
            outgoing.flush();

            // Get nonce from client
            String cNonceString = incoming.readLine();
            cNonce = Integer.valueOf(cNonceString);
            System.out.println("Step 2: Received and Saved Client Packet");
            System.out.println("Save Client Nonce: " + cNonce);

            // Send Servers key values 
            outgoing.writeBytes(String.valueOf(publicKey.getN()) + " " + String.valueOf(publicKey.getE()) + "\r");
            outgoing.flush();

            // Get master key, encrypted with public key 
            int encryptedMasterKey;
            String masterReader;
            masterReader = incoming.readLine();
            encryptedMasterKey = Integer.valueOf(masterReader);
            System.out.println("Client Encrypted Master Key: " + encryptedMasterKey);

            // Decrypt master key using server private key
            int decrytpedMastKey;
            decrytpedMastKey = privateKey.decryptPrivateKey(encryptedMasterKey);
            System.out.println("Decrypted Master Key: " + decrytpedMastKey);

            // Remove nonces from master key
            int masterKey;
            masterKey = decrytpedMastKey - sNonce - cNonce;
            System.out.println("Master Key after nonces removed: " + masterKey);

            System.out.println("Connected.  Waiting for the first message.");
        }
        catch (Exception error) {
            System.out.println("An error occurred while opening connection.");
            System.out.println(error.toString());
            return;
        }

        try {

            userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("NOTE: Enter 'quit' to end the program.\n");
            while (true) {
                System.out.println("WAITING...");
                messageIn = incoming.readLine();
                if (messageIn.length() > 0) {
                    // The first character of the message is a command. If
                    // the command is CLOSE, then the connection is closed.
                    // Otherwise, remove the command character from the
                    // message and procede.
                    
                    typeString = messageIn.substring(0, 1);
                    System.out.println("Type (Client): " + typeString);
                    int clientType = Integer.valueOf(typeString);
                    
                    lengthString = messageIn.substring(1, 3);
                    length = Integer.valueOf(lengthString);
                    System.out.println("Length (Client): " + lengthString);
                    
                    clientSeqNumString = messageIn.substring(3,4);
                    System.out.println("Sequence Number (Client): " + clientSeqNumString);


                    String messageEncrypted = messageIn.substring(4);
                    System.out.println("EncryptedMessage: " + messageEncrypted);
                    String messageDecrypted = clientDecrypt(messageEncrypted);
                    System.out.println("DecryptedMessage: " + messageDecrypted);

                    hashNumString = messageDecrypted.substring(messageDecrypted.length() - 1);

                    int Mc = generateClientMACKey(MASTER_KEY, cNonce, sNonce);

                    System.out.println("Step 7: Check if macs are equal");
                    String hashNumString2 = hashDecrypt(clientType, clientSeqNum, Mc, messageDecrypted.substring(0, messageDecrypted.length() - 1));
                    System.out.println("Hash: " + hashNumString);
                    System.out.println("hashNumString2:" + hashNumString2);
                    if(!hashNumString.equals(hashNumString2)){
                        throw new Exception("Macs don't match!");
                    }

                    String messageBefore = messageDecrypted.substring(0, messageDecrypted.length() - 1);
                    System.out.println("messageBefore: " + messageBefore);

                    String messageFinal = stringConvertBack(messageBefore);
                    System.out.println("RECEIVED:  " + messageFinal);

                    if (messageIn.charAt(0) == CLOSE) 
                    {
                        System.out.println("Connection closed at other end.");
                        connection.close();
                        break;
                    }
                }
                System.out.print("SEND:      ");
                messageOut = userInput.readLine();
                if (messageOut.equals("quit"))  
                {
                    type = 1;
                    typeString = "1";
                    System.out.println("Step 4: Server Extracts data from Packets and Generate Mac Keys ");
                    int Ms = generateServerMACKey(MASTER_KEY, cNonce, sNonce);
                    System.out.println("Ms: " + Ms);
                    System.out.println("Sequence Number (Server): " + serverSeqNum);
                    hashNumString = hashEncrypt(serverSeqNum, type, Ms, messageOut);
                    System.out.println("Hash: " + hashNumString);
                    String message = stringConvert(messageOut);

                    length = message.length();

                    String messageFull = message + hashNumString;
                    System.out.println("Message: " + message);
                    System.out.println("message + Hash: " + messageFull);
                    String encryptedMessage = serverEncryptionKey(messageFull);
                    System.out.println("Encrypted messageBeforeType+Length: " + encryptedMessage);
                    encryptedMessage = "1" + lengthString + serverSeqNum + encryptedMessage;

                    System.out.println("Encrypted message After: " + encryptedMessage);

                    outgoing.writeBytes(encryptedMessage + "\r");
                    outgoing.flush();

                    serverSeqNum++;

                    connection.close();
                    System.out.println("Connection closed.");
                    break;
                }
                type = 0;
                int Ms = generateServerMACKey(MASTER_KEY, cNonce, sNonce);
                System.out.println("Ms: " + Ms);
                System.out.println("Sequence Number (Server): " + serverSeqNum);
                System.out.println("Step 5: Send out Mac server keys");
                hashNumString = hashEncrypt(serverSeqNum, type, Ms, messageOut);
                System.out.println("Hash: " + hashNumString);
                String message = stringConvert(messageOut);

                length = message.length();

                String messageFull = message + hashNumString;
                System.out.println("Message: " + message);
                System.out.println("Message + Hash: " + messageFull);
                String encryptedMessage = serverEncryptionKey(messageFull);
                System.out.println("Encrypted messageBefore (Type + Length + Seq): " + encryptedMessage);
                typeString = String.valueOf(type);
                if(length < 10){
                    lengthString = "0" + String.valueOf(length);
                }else{
                    lengthString = String.valueOf(length);
                }
                encryptedMessage = typeString + lengthString + serverSeqNum + encryptedMessage;

                System.out.println("Encrypted message after (Type + Length + Seq) added: " + encryptedMessage);

                outgoing.writeBytes(encryptedMessage + "\r");
                outgoing.flush();

                serverSeqNum++;
            }
        }
        catch (Exception error) {
            System.out.println("Sorry, an error has occurred.  Connection lost.");
            System.out.println("Error:  " + error);
            System.exit(1);
        }
    }  // end main()

    // Ks shared encryption key
    public static String serverEncryptionKey(String message)
    {
        BigInteger bigintMessage = new BigInteger(message);
        BigInteger masterSecret = BigInteger.valueOf(MASTER_KEY);
        BigInteger bigCNonce = BigInteger.valueOf(cNonce);
        BigInteger bigSNonce = BigInteger.valueOf(sNonce);

        masterSecret = masterSecret.add(bigSNonce);
        masterSecret = masterSecret.subtract(bigCNonce);

        System.out.println("CNonce: " + bigCNonce);
        System.out.println("SNonce: " + bigSNonce);
        System.out.println("masterSecret: " + masterSecret);

        bigintMessage = bigintMessage.add(masterSecret);
        String result;
        result = String.valueOf(bigintMessage);
        if(result.length() % 2 == 0)
        {
            result = "0" + result;
        }
        return result;
    }
    // Server decrypts using shared secret from client 
    public static String clientDecrypt(String messageEncrypted)
    {
        String result;
        BigInteger bigMessage = new BigInteger(messageEncrypted);
        BigInteger masterSecret = BigInteger.valueOf(MASTER_KEY);
        BigInteger bigCNonce = BigInteger.valueOf(cNonce);
        BigInteger bigSNonce = BigInteger.valueOf(sNonce);

        masterSecret = masterSecret.subtract(bigCNonce);
        masterSecret = masterSecret.add(bigSNonce);
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
    // Hashes for MAC 
    public static String hashEncrypt(int seq, int type, int ms, String message){
        String result = "";
        int number = 5;
        String m = stringConvert(message);
        BigInteger bigRand = BigInteger.valueOf(number);
        BigInteger big =  new BigInteger(seq + type + ms + m);
        big = big.mod(bigRand);
        result = String.valueOf(big);
        return result;
    }
    // Decrypts Hashes for MAC 
    public static String hashDecrypt(int seq, int type, int mc, String message){
        String result = "";
        int number = 5;
        BigInteger bigRand = BigInteger.valueOf(number);
        BigInteger big =  new BigInteger(seq + type + mc + message);
        big = big.mod(bigRand);
        result = String.valueOf(big);
        return result;
    }
    // convert message to integers 
    public static String stringConvert(String message) {
        String result = "";
        for(int i = 0; i < message.length(); i++) {
            result += convertChartoNumber(message.charAt(i));
        }
        return result;
    }
    public static String convertChartoNumber(char c) 
    {
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
    // Converts integers back to numbers
    public static String converCharBackToString(String c) {
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
            result = result + converCharBackToString(temp);
        }
        return result;
    }
    // generate Mc for CLient and Ms Server 
    public static int generateServerMACKey(int masterKey, int cNonce, int sNonce)
    {
        int result = 0;
        result = masterKey - cNonce - sNonce;
        return result;
    }
    
    public static int generateClientMACKey(int masterKey, int cNonce, int sNonce)
    {
        int result = 0;
        result = masterKey + cNonce + sNonce;
        return result;
    }

} //end class ChatServer