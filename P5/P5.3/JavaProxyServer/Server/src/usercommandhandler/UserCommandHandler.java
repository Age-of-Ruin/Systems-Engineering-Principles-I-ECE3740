package usercommandhandler;


public class UserCommandHandler {
    standardio.StandardIO myUI;
    server.Server myServer;
    mx7serverconnection.Client mx7Client;

    public UserCommandHandler(standardio.StandardIO myUI, server.Server myServer, mx7serverconnection.Client mx7Client) {
        this.myUI = myUI;
        this.myServer = myServer;
        this.mx7Client = mx7Client;
    }

    public void execute(String theCommand) {
        
        switch (Integer.parseInt(theCommand)) {
            case 1: //START SERVER SOCKET
                myServer.startServer();
                myUI.update("Server Socket has been created.");
                break;
            case 2: //LISTEN
                myUI.update("Server is now listening, ...");
                myServer.listen();
                break;
            case 3: //CONNECT TO MX7 SERVER
                if(!mx7Client.isConnected()){
                    myUI.update("Connecting to MX7 server...");
                    mx7Client.connectToServer();   
                }
                else
                    myUI.update("Already connected to server.");
                break;
            case 4: //DISCONNECT FROM MX7 SERVER
                if(!mx7Client.isConnected()){
                    myUI.update("Please connect to server first.");
                }
                else{
                    myUI.update("Disconnecting from MX7 server...");
                    mx7Client.sendMessageToServer((byte) 'd');
                    mx7Client.terminateMessage();
                }
                break;
            case 5: //Get Temp
                if(mx7Client.isConnected()){
                    myUI.update("Getting Temperature...");
                    mx7Client.sendMessageToServer((byte)'t');
                    mx7Client.terminateMessage();
                }
                else
                    myUI.update("Please connect to server first.");
                break;       
            case 6: //QUIT
                myServer.stopServer();
                myUI.update("Quitting program by User command.");
                if(mx7Client.isConnected()){
                    mx7Client.sendMessageToServer((byte) 'q');
                    mx7Client.terminateMessage();
                }
                else
                    System.exit(0);
                break;
            default:
                break;
        }
    }
}
