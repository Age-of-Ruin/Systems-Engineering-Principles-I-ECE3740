package com.constantine.rick.androidclient.usercommandhandler;

public class UserCommandHandler implements Runnable {

    com.constantine.rick.androidclient.clientGUI.ClientGUI myUI = null;
    com.constantine.rick.androidclient.client.Client myClient = null;
    String theCommand;

    public UserCommandHandler(com.constantine.rick.androidclient.clientGUI.ClientGUI myUI, com.constantine.rick.androidclient.client.Client myClient) {
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
        if (theCommand.equals("Connect")) { //CONNECT
            if (!myClient.isConnected())
                myClient.connectToServer();
            else
                myUI.update("Client already connected to server.");

        } else if (theCommand.equals("Disconnect")) { //DISCONNECT
            if (myClient.isConnected()) {
                myClient.sendMessageToServer((byte) 'd');
                myClient.terminateMessage();
            } else {
                myUI.update("Please connect to server first.");
            }

        } else if (theCommand.equals("Get Time")) {  //GET TIME
            if (myClient.isConnected()) {
                myClient.sendStringToServer("time");
                myClient.terminateMessage();
            } else {
                myUI.update("Please connect to server first.");
            }
        } else if (theCommand.equals("Get Temp")) {  //GET TEMP
            if (myClient.isConnected()) {
                myClient.sendStringToServer("temp");
                myClient.terminateMessage();
            } else {
                myUI.update("Please connect to server first.");
            }
        } else if (theCommand.equals("Quit")) {  //Quit
            if (myClient.isConnected()) {
                myClient.sendMessageToServer((byte) 'q');
                myClient.terminateMessage();
            } else {
                System.exit(0);
            }
        } else if (theCommand.contains("New Port")) {  //New Port Number
            if (myClient.isConnected()) {
                myUI.update("Please disconnect from server first.");
            } else {
                String[] words = theCommand.split("\\s");
                int newPort = Integer.parseInt(words[2]);
                myClient.setPort(newPort);
                myUI.update("Port set to " + newPort);
            }
        } else if (theCommand.contains("New IP")) {  //New Port Number
            if (myClient.isConnected()) {
                myUI.update("Please disconnect from server first.");
            } else {
                String[] words = theCommand.split("\\s");
                String newIP = words[2];
                myClient.setIP(newIP);
                myUI.update("IP set to " + newIP);
            }
        } else if (theCommand.equals("L1ON")) {  //LED1
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L1On");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L1OFF")) {
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L1Off");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L2ON")) {  //LED2
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L2On");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L2OFF")) {
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L2Off");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L3ON")) {  //LED3
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L3On");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L3OFF")) {
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L3Off");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L4ON")) {  //LED4
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L4On");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L4OFF")) {  //LED4
            if (myClient.isConnected()) {
                myClient.sendStringToServer("L4Off");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("GPB1")) { //PB1
            if (myClient.isConnected()) {
                myClient.sendStringToServer("gpb1");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("GPB2")) { //PB2
            if (myClient.isConnected()) {
                myClient.sendStringToServer("gpb2");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("GPB3")) { //PB3
            if (myClient.isConnected()) {
                myClient.sendStringToServer("gpb3");
                myClient.terminateMessage();
            } else
                myUI.update("Please connect to server first.");


        }
    }
}
