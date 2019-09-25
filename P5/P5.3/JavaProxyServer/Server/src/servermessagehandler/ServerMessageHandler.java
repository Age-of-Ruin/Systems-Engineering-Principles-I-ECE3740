package servermessagehandler;

public class ServerMessageHandler {

    mx7serverconnection.Client mx7Client;
    clientconnection.ClientConnection myClientConnection;
    int count = 0;

    public ServerMessageHandler(mx7serverconnection.Client mx7Client) {
        this.mx7Client = mx7Client;
    }

    public void handleServerMessage (byte msg){

        String serverString = getServerString(msg);
        myClientConnection = mx7Client.getClientConnection();
        
        if(count != 0){
            if(serverString.charAt(0) == 'D')
                mx7Client.disconnectFromServer();
            else if(serverString.charAt(0) == 'Q') {
                mx7Client.disconnectFromServer();
                System.exit(0);
            }
            if(myClientConnection != null){
                myClientConnection.sendStringToClient(serverString);
                myClientConnection.terminateMessage();
            }
            
            mx7Client.sendMessageToUI(serverString);

            count = 0;
        }
    }

    public void handleServerMessage (String msg){
        System.err.println(msg);
    }

    public String getServerString(byte msg){
        byte[] serverMSG = new byte[100];
        byte serverByte = msg;

        serverMSG[count] = msg;

        while(serverByte != -1){
            count++;
            try{
                serverByte = mx7Client.getMessageFromServer();
                if(serverByte != -1)
                    serverMSG[count]  = serverByte;
            }
            catch (Exception e){
                mx7Client.sendMessageToUI("Cannot convert server response to string.");
            }
        }
        String serverString = new String(serverMSG);
        return serverString.trim();
    }
    

}
