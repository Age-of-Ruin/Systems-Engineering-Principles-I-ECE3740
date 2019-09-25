package clienttest;

import java.net.*;
import java.io.*;

public class clienttest {
      
    public static void main(String[] args) {
        
    userinterface.StandardIO myUI = new userinterface.StandardIO();
    byte msg = ' ';
    int portNumber = 5555;
    InetAddress IPAddress = null;
    
    // Get Local Host IP (both Server and Client must run on local computer)
    try{
        IPAddress = InetAddress.getLocalHost();
    }
    catch(Exception e){
        System.err.println("Cannot find local IP.");
        System.exit(1);
    }
    
    //Create Client Socket & Setup Communication Streams
    client.Client myClient = new client.Client(IPAddress, portNumber);
    myClient.setupStreams();
    
//    myUI.display(myClient.getLocalIP());
//    myUI.display(myClient.getLocalPort());
//    myUI.display(myClient.getRemoteIP());
//    myUI.display(myClient.getRemotePort());
    
    myUI.display("d:\tDisconnect\nt:\tGet Time");
        boolean connected = true;
        while (connected) {
            String myCommand = myUI.getUserInput();
            
            if(myCommand.equals("d")){
                myUI.display("Disconnecting...");
                myClient.sendMessageToServer((byte) 'd');
                
                boolean awaitACK = true;
                while (awaitACK == true){
                    if((char) myClient.getMessageFromServer() == 'd')
                        awaitACK = false;
                }
                
                myClient.disconnectClient();
                connected = false;
            }
            
            else if(myCommand.equals("t")){
                myUI.display("Getting Time...");
                myClient.sendMessageToServer((byte) 't');
                
                String time = myClient.getByteStream();
                myUI.display(time);
                myUI.display("Enter another command");    
                }
    
            else
                myUI.display("Command not recognized.");
        }//while
    
        myUI.display("Program Terminated");
        
    }//main

}
