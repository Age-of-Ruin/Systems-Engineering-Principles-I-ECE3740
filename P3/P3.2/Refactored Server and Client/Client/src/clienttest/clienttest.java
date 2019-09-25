package clienttest;

import java.net.*;
import java.io.*;

public class clienttest {
      
    public static void main(String[] args) {
        
    standardiouserinterface.StandardIO myUI = new standardiouserinterface.StandardIO();
    client.Client myClient = new client.Client(5555, myUI);
    usercommandhandler.UserCommandHandler myUserCommandHandler = new usercommandhandler.UserCommandHandler(myUI, myClient);
    myUI.setCommandHandler(myUserCommandHandler);
    Thread theUIThread = new Thread(myUI);
    theUIThread.start();
    myUI.update("1:\tCreate Socket/Thread & Connect\n2:\tDisconnect and Stop"
                + "Socket/Thread\n3:\tGet Time\n4:\tQuit\n\nEnter command");
        
    }//main

}
