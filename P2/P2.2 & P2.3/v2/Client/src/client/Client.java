package client;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client implements Runnable {

    InputStream input;
    OutputStream output;
    Socket clientSocket;
    servercommandhandler.ServerCommandHandler myServerCommandHandler;
    userinterface.StandardIO myUI;
    boolean stopThisThread = false;
    boolean isConnected = false;
    int portNumber = 5555;
    InetAddress IPAddress;
    
    public Client(int portNumber, userinterface.StandardIO myUI){
        this.portNumber = portNumber;
        this.myUI = myUI;
        this.myServerCommandHandler = new servercommandhandler.ServerCommandHandler(this);
    }

    public void connectToServer(){
        try{
            isConnected = true;
            IPAddress = InetAddress.getLocalHost();
            clientSocket = new Socket(IPAddress, portNumber);
            setupStreams(); 
            
            Thread mySCThread = new Thread(this);
            mySCThread.start();
            stopThisThread = false;
        
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public void disconnectFromServer() {
        try {
            isConnected = false;
            stopThread();
            clientSocket.close();
            input = null;
            output = null;
            clientSocket = null;
        } catch (IOException e) {
            System.err.println("cannot close stream/client socket; exiting.");
            System.exit(1);
        }
    }       
       
    public void sendMessageToServer(byte msg) {
        try {
            output.write(msg);
            output.flush();
        } catch (IOException e) {
            System.err.println("cannot send to socket; exiting program.");
            System.exit(1);
        }
    }
    
    public boolean isConnected(){        
        return isConnected;
    }
    
    public void stopThread(){
        stopThisThread = true;
    }
    
    public String getLocalPort(){
        return Integer.toString(clientSocket.getLocalPort());
    }
    
    public String getLocalIP(){
        return clientSocket.getLocalAddress().toString();
    }
    
    public String getRemotePort(){
        return Integer.toString(clientSocket.getPort());
    }
    
    public String getRemoteIP(){
        return clientSocket.getInetAddress().toString();
    }
    
    public String getLSocketAddress(){
        return clientSocket.getLocalSocketAddress().toString();
    }
    
    public String getRSocketAddress(){
        return clientSocket.getRemoteSocketAddress().toString();
    }
    
    public void setPort(int portNum){
        portNumber = portNum;
    }
    
    public void sendMessageToUI(String msg){
        myUI.display(msg);
    }
    
    @Override
    public void run(){
        while(stopThisThread == false){
            myServerCommandHandler.execute(getMessageFromServer());
        }
    } 
    
    // *************** Extra Methods******************//
    public void setupStreams() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException e) {
            System.err.println("cannot open communication streams; exiting program.");
            System.exit(1);
        }
    }
    
    public byte getMessageFromServer() {
        byte msg = -1;
        try {
            msg = (byte) input.read();
        } catch (IOException e) {
            throw new IOException ("READ ERROR");
        } finally {
            return msg;
        }
    }
    
}
    
