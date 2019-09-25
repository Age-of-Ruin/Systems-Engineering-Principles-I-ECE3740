
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
            if(serverString.trim().equals("Disconnect")){
                myClient.disconnectFromServer();
                myClient.sendMessageToUI("Disconnected.");
            }
            else
                myClient.sendMessageToUI(serverString);
            

            
            count = 0;
        }
    }
        
    public void handleServerMessage (String msg){
        System.err.println(msg);
    }
    
    public String getServerString(byte msg){
    //Save first byte of time
        byte[] serverMSG = new byte[100];
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
