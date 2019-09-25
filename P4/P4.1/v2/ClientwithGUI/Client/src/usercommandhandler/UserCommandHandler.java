package usercommandhandler;

public class UserCommandHandler implements Runnable {

    userinterface.UserInterface myUI;
    client.Client myClient = null;
    String theCommand;

    public UserCommandHandler(userinterface.UserInterface myUI, client.Client myClient) {
        this.myUI = myUI;
        this.myClient = myClient;
    }
    
    public void execute (String myCommand){
        theCommand = myCommand;
        Thread myCommandThread = new Thread(this);
        myCommandThread.start();
    }
    
    @Override
    public void run() {
        if (theCommand.equals("1")) { //CONNECT
            if (!myClient.isConnected()) {
                myUI.update("Creating socket, starting thread, and connecting to local server...");
                myClient.connectToServer();
                myUI.update("Local Socket Address: " + myClient.getLSocketAddress());
                myUI.update("Remote Socket Address: " + myClient.getRSocketAddress());
            } else {
                myUI.update("Client " + myClient.getLSocketAddress() + " is already connected to server.");
                myUI.update("\nEnter another command.");
            }
        } else if (theCommand.equals("2")) { //DISCONNECT
            if (myClient.isConnected()) {
                myUI.update("Disconnecting...");
                myClient.sendMessageToServer((byte) 'q');
                myClient.terminateMessage();
            } else {
                myUI.update("Not yet connected to server.");
                myUI.update("\nEnter another command.");
            }
        } else if (theCommand.equals("3")) {  //GET TIME
            if (myClient.isConnected()) {
                myUI.update("Getting Time...");
                myClient.sendMessageToServer((byte) 't');
                myClient.terminateMessage();
            } else {
                myUI.update("Please connect to server first.");
                myUI.update("\nEnter another command.");
            }
        } else if (theCommand.equals("4")) { //GET TEMP
            if (myClient.isConnected()) {
                myUI.update("Getting Temperature...");
                myClient.sendMessageToServer((byte) 't');
                myClient.terminateMessage();
            } else {
                myUI.update("Please connect to server first.");
                myUI.update("\nEnter another command.");
            }
        } else if (theCommand.equals("5")) { //QUIT
            if (myClient.isConnected()) {
                myClient.sendMessageToServer((byte) 'q');
                myClient.terminateMessage();
            }
            myUI.update("Quiting program by User command.");
            System.exit(0);
        } else if (theCommand.equals("L1on")) { //L1ON
            myClient.sendStringToServer("L1on");
            myClient.terminateMessage();
        } else if (theCommand.equals("L1off")) { //L1OFF
            myClient.sendStringToServer("L1off");
            myClient.terminateMessage();
        } else if (theCommand.equals("L2on")) { //L2ON
            myClient.sendStringToServer("L2on");
            myClient.terminateMessage();
        } else if (theCommand.equals("L2off")) { //L2OFF
            myClient.sendStringToServer("L2off");
            myClient.terminateMessage();
        } else if (theCommand.equals("L3on")) { //L3ON
            myClient.sendStringToServer("L3on");
            myClient.terminateMessage();
        } else if (theCommand.equals("L3off")) { //L3OFF
            myClient.sendStringToServer("L3off");
            myClient.terminateMessage();
        } else if (theCommand.equals("L4on")) { //L4ON
            myClient.sendStringToServer("L4on");
            myClient.terminateMessage();
        } else if (theCommand.equals("L4off")) { //L4OFF
            myClient.sendStringToServer("L4off");
            myClient.terminateMessage();
        } else if (theCommand.equals("gpb1")) { //L1ON
            myClient.sendStringToServer("gpb1");
            myClient.terminateMessage();
        } else if (theCommand.equals("gpb3")) { //L1ON
            myClient.sendStringToServer("gpb3");
            myClient.terminateMessage();
        } else if (theCommand.equals("gpb3")) { //L1ON
            myClient.sendStringToServer("gpb3");
            myClient.terminateMessage();           
        } else {
            myUI.update("Command not recognized");
            myUI.update("\nEnter another command.");

        }
    }
}
