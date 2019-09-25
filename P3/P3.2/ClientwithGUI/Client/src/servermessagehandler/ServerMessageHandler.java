
package servermessagehandler;

public class ServerMessageHandler {

    client.Client myClient;
    int count = 0;
    
    public ServerMessageHandler(client.Client myClient) {
        this.myClient = myClient;
    }
    
    public void handleServerMessage (byte msg){

        String serverString = getServerString(msg);
        
        if(count != 0){ 

            myClient.sendMessageToUI(serverString);
            
            //Disconnect ACK 
            if(serverString.charAt(0) == 'D'){        
                myClient.disconnectFromServer();
            }
            
            //Quit Ack
            if(serverString.charAt(0) == 'Q'){        
                myClient.disconnectFromServer();
                System.exit(0);
            }

            count = 0;
        }
    }
        
    public void handleServerMessage (String msg){
        System.err.println(msg);
    }
    
    public String getServerString(byte msg){
    //Save first byte of time
        byte[] serverMSG = new byte[500];
        byte serverByte = msg;
        
        serverMSG[count] = msg;
        
        while(serverByte != -1){
            count++;
            try{
                serverByte = myClient.getMessageFromServer();
                if(serverByte != -1)
                    serverMSG[count]  = serverByte;
            }
            catch (Exception e){
                myClient.sendMessageToUI("Cannot convert server response to string.");
            }
        }
        String serverString = new String(serverMSG);
        return serverString;
    }
}
