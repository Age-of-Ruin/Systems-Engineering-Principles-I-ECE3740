/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmessagehandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ferens
 */
public class ClientMessageHandler {

    standardiouserinterface.StandardIO myUI;
    server.Server myServer;

    public ClientMessageHandler(server.Server myServer) {
        this.myServer = myServer;
    }
    
    public void handleClientMessage(clientconnection.ClientConnection myClientConnection, String theCommand) {
        byte msg;
        if (theCommand.equals("d")) {
            myServer.sendMessageToUI("Disconnect command "
                    + "received from client " + myClientConnection.getClientSocket().getRemoteSocketAddress());
            myClientConnection.clientDisconnect();
            myServer.sendMessageToUI("\tDisconnect "
                    + "successful. ");
        } else if (theCommand.equals("q")) {
            myServer.sendMessageToUI("Quit command "
                    + "received from client " + myClientConnection.getClientSocket().getRemoteSocketAddress());
            myClientConnection.clientQuit();
            myServer.sendMessageToUI("\tQuit successful. ");
        } else if (theCommand.equals("t")) {
            myServer.sendMessageToUI("Get Time command "
                    + "received from client " + myClientConnection.getClientSocket().getRemoteSocketAddress());
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            for (int i = 0; i < sdf.format(cal.getTime()).length(); i++) {
                msg = (byte) sdf.format(cal.getTime()).charAt(i);
                myClientConnection.sendMessageToClient(msg);
            }
            myServer.sendMessageToUI("\tClient given time: "+sdf.format(cal.getTime()));
        }
    }

    public void handleClientMessage(String theExceptionalEvent) {
        myServer.sendMessageToUI(theExceptionalEvent);
    }

 }
