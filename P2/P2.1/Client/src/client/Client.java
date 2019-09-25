package client;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    InputStream input;
    OutputStream output;
    Socket clientSocket = null;
    byte msg = ' ', clientCommand = ' ';
    int portNumber = 5555;
    InetAddress IPAddress;
    
    public Client(InetAddress IPAddress, int portNumber){
        this.IPAddress = IPAddress;
        this.portNumber = portNumber;
        
        try{
            clientSocket = new Socket(IPAddress, portNumber);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    public String getByteStream(){
            
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte rcvdByte = 0;
        String time = "No time sent";
        
        try{
            for(int i = 0; i < 8; i ++){
                rcvdByte = getMessageFromServer();
                baos.write(rcvdByte);
                
                //for(int i = 0; i < 1000; i ++){}
                //System.out.println(rcvdByte);

            }
            
        byte[] bytes = baos.toByteArray();
        String s = new String(bytes);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date d = df.parse(s);
        
        time = df.format(d);
        
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);  
        }
        
        return time;
    }
    
    public byte getMessageFromServer() {
        try {
            msg = (byte) input.read();
        } catch (IOException e) {
            System.err.println("cannot read from socket; exiting program.");
            System.exit(1);
        } finally {
            return msg;
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
    
    public void setupStreams() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException e) {
            System.err.println("cannot open communication streams; exiting program.");
            System.exit(1);
        }
    }
    
    public void disconnectClient() {
        try {
            clientSocket.close();
            input = null;
            output = null;
            clientSocket = null;
        } catch (IOException e) {
            System.err.println("cannot close stream/client socket; exiting.");
            System.exit(1);
        }
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
       
}
    
