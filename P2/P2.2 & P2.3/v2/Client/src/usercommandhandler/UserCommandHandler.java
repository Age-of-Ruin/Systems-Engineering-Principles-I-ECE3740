package usercommandhandler;

public class UserCommandHandler {
    userinterface.StandardIO myUI;
    client.Client myClient = null;

    public UserCommandHandler(userinterface.StandardIO myUI, client.Client myClient) {
        this.myUI = myUI;
        this.myClient = myClient;
    }

    public void execute(String theCommand) {
        int myUserCommand;
        try {
            myUserCommand = Integer.parseInt(theCommand);
        }
        catch (NumberFormatException nfe) {
            myUI.display("Please enter a valid command.");
            return;
        }
        switch (myUserCommand) {
            case 1: //CONNECT
                if(!myClient.isConnected()){
                    myUI.display("Creating socket, starting thread, and connecting to local server...");
                    myClient.connectToServer();
                    myUI.display("Connected.");
                    myUI.display("Local Socket Address: " + myClient.getLSocketAddress());
                    myUI.display("Remote Socket Address: " + myClient.getRSocketAddress());
                    myUI.display("\nEnter another command.");
                }
                else{
                    myUI.display("Client " + myClient.getLSocketAddress() + " is already connected to server.");
                    myUI.display("\nEnter another command.");

                }
                break;
            case 2: //DISCONNECT
                if(myClient.isConnected()){
                    myUI.display("Disconnecting...");
                    myClient.sendMessageToServer((byte) 'd');
                    myClient.disconnectFromServer();
                    myUI.display("Disconnected from Server");
                    myUI.display("\nEnter another command.");
                }
                else{
                    myUI.display("Not yet connected to server.");
                    myUI.display("\nEnter another command.");
                }                    
                break;
            case 3: //REQUEST TIME
                if(myClient.isConnected()){
                    myUI.display("Getting Time...");
                    myClient.sendMessageToServer((byte) 't');
                }
                else{
                    myUI.display("Please connect to server first.");
                    myUI.display("\nEnter another command.");
                }
                break;
            case 4: //QUIT
                myUI.display("Quiting program by User command.");
                myClient.sendMessageToServer((byte) 'q');
                System.exit(0);
            default:
                break;
        }
    }
}
