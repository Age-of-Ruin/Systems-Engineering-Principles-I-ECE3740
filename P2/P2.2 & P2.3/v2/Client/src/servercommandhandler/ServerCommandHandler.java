
package servercommandhandler;

import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ServerCommandHandler {

    client.Client myClient;
    byte serverByte;
    byte[] serverMSG = new byte[8];
    
    public ServerCommandHandler(client.Client myClient) {
        this.myClient = myClient;
    }
    
    public void execute (byte msg){
        
        //Save first byte of time
        serverByte = msg;
        serverMSG[0] = serverByte;
        
        //Save rest of bytes of time    
        for (int i = 0; i < 7; i ++){
            serverByte = myClient.getMessageFromServer();
            //System.out.println(serverByte);
            serverMSG[i+1] = serverByte;
        }        
        
        try{        
        String s = new String(serverMSG);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date d = df.parse(s);
        
        String time = df.format(d);
        myClient.sendMessageToUI(time);
        myClient.sendMessageToUI("\nEnter another command.");
        }
        catch (Exception e){
            //myClient.sendMessageToUI("Cannot convert server response to time.");
            //myClient.sendMessageToUI("\nEnter another command.");
        }
    }

}
