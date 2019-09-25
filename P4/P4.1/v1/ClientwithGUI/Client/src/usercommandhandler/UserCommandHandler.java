package usercommandhandler;

public class UserCommandHandler {
    userinterface.UserInterface myUI;
    client.Client myClient = null;

    public UserCommandHandler(userinterface.UserInterface myUI, client.Client myClient) {
        this.myUI = myUI;
        this.myClient = myClient;
    }

    public void execute(String theCommand) {
        int myUserCommand;
        try {
            myUserCommand = Integer.parseInt(theCommand);
        }
        catch (NumberFormatException nfe) {
            myUI.update("Please enter a valid command.");
            return;
        }
        switch (myUserCommand) {
            case 1: //CONNECT
                if(!myClient.isConnected()){
                    myUI.update("Creating socket, starting thread, and connecting to local server...");
                    myClient.connectToServer();
                    myUI.update("Local Socket Address: " + myClient.getLSocketAddress());
                    myUI.update("Remote Socket Address: " + myClient.getRSocketAddress());
                }
                else{
                    myUI.update("Client " + myClient.getLSocketAddress() + " is already connected to server.");
                    myUI.update("\nEnter another command.");
                }
                break;
            case 2: //DISCONNECT
                if(myClient.isConnected()){
                    myUI.update("Disconnecting...");
                    myClient.sendMessageToServer((byte) 'q');
                    myClient.terminateMessage();
                }
                else{
                    myUI.update("Not yet connected to server.");
                    myUI.update("\nEnter another command.");
                }                    
                break;
            case 3: //REQUEST TIME
                if(myClient.isConnected()){
                    myUI.update("Getting Time...");
                    myClient.sendMessageToServer((byte) 't');
                    myClient.terminateMessage();
                }
                else{
                    myUI.update("Please connect to server first.");
                    myUI.update("\nEnter another command.");
                }
                break;
            case 4:
                if(myClient.isConnected()){
                    myUI.update("Getting Temperature...");
                    myClient.sendMessageToServer((byte) 't');
                    myClient.terminateMessage();
                }
                else{
                    myUI.update("Please connect to server first.");
                    myUI.update("\nEnter another command.");
                }
                break;
            case 5: //QUIT
                if(myClient.isConnected()){
                    myClient.sendMessageToServer((byte) 'q');
                    myClient.terminateMessage();
                }
                myUI.update("Quiting program by User command.");
                System.exit(0);
            default:
                break;
        }
    }
}
