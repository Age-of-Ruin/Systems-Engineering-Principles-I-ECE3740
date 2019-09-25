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
    servermessagehandler.ServerMessageHandler myServerMessageHandler;
    userinterface.UserInterface myUI;
    boolean stopThisThread = false;
    boolean isConnected = false;
    int portNumber = -1;
    InetAddress IPAddress;
    
    public Client(String IPAddress, int portNumber, userinterface.UserInterface myUI){
        try{
            this.IPAddress = InetAddress.getByName(IPAddress);
        }
        catch (Exception e){
            myUI.update("Cannot get IPaddress - setting to local IP.");
        }
        this.portNumber = portNumber;
        this.myUI = myUI;
        this.myServerMessageHandler = new servermessagehandler.ServerMessageHandler(this);
    }

    public void connectToServer(){
        try{
            isConnected = true;
            if(IPAddress == null){
                IPAddress = InetAddress.getLocalHost();
            }
            if(portNumber == -1)
                portNumber = 5555;
            
            clientSocket = new Socket(IPAddress, portNumber);
            setupStreams();
            
            Thread mySCThread = new Thread(this);
            mySCThread.start();
            stopThisThread = false;
        
        }
        catch (Exception e){
            myServerMessageHandler.handleServerMessage(e.getMessage());
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
            myServerMessageHandler.handleServerMessage("cannot close stream/client socket; exiting.");
            System.exit(1);
        }
    }       
       
    public void sendMessageToServer(byte msg) {
        try {
            output.write(msg);
            output.write(0xFF);
            output.flush();
        } catch (IOException e) {
            myServerMessageHandler.handleServerMessage("cannot send to socket; exiting program.");
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
        myUI.update(msg);
    }
    
    @Override
    public void run(){
        while(stopThisThread == false){
            myServerMessageHandler.handleServerMessage(getMessageFromServer());
        }
    } 
    
    // *************** Extra Methods******************//
    public void setupStreams() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException e) {
            myServerMessageHandler.handleServerMessage("cannot open communication streams; exiting program.");
            System.exit(1);
        }
    }
    
    public byte getMessageFromServer() {
        byte msg = -1;
        try {
            msg = (byte) input.read();
        } catch (IOException e) {
            if(clientSocket != null){
                myServerMessageHandler.handleServerMessage("read error - disconnecting client and stopping thread");
                disconnectFromServer();
            }
        } finally {
            return msg;
        }
    }
    
}
    
