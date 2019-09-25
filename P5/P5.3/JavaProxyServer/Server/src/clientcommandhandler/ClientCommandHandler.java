package clientcommandhandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientCommandHandler {

    userinterface.UserInterface myUI;
    mx7serverconnection.Client mx7Client;

    public ClientCommandHandler(standardio.StandardIO myUI, mx7serverconnection.Client mx7Client) {
        this.myUI = myUI;
        this.mx7Client = mx7Client;
    }

    public void execute(clientconnection.ClientConnection myClientConnection, String theCommand) {
        byte msg;
        
        if (theCommand.charAt(0) == 'd') {
            myUI.update("Client has disconnected:\n\tRemote Socket Address = "+myClientConnection.getClientSocket().getRemoteSocketAddress() + "\n\tLocal Socket Address = " +myClientConnection.getClientSocket().getLocalSocketAddress());
            mx7Client.setClientConnection(null);
            myClientConnection.disconnectClient();
        } else if (theCommand.charAt(0) == 'q') {
            myUI.update("Client has quit:\n\tRemote Socket Address = "+myClientConnection.getClientSocket().getRemoteSocketAddress() + "\n\tLocal Socket Address = " +myClientConnection.getClientSocket().getLocalSocketAddress());
            mx7Client.setClientConnection(null);
            myClientConnection.quitClient();
        
        } else if (theCommand.equals("L1On")) {  //LED1
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L1On");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L1Off")) {
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L1Off");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L2On")) {  //LED2
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L2On");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L2Off")) {
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L2Off");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L3On")) {  //LED3
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L3On");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");

        } else if (theCommand.equals("L3Off")) {
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L3Off");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L4On")) {  //LED4
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L4On");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("L4Off")) {  //LED4
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("L4Off");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("gpb1")) { //PB1
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("gpb1");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("gpb2")) { //PB2
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("gpb2");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
        } else if (theCommand.equals("gpb3")) { //PB3
            if (mx7Client.isConnected()) {
                mx7Client.sendStringToServer("gpb3");
                mx7Client.terminateMessage();
            } else
                myUI.update("Please connect to server first.");
            
        } else if (theCommand.contains("time")) {
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            myClientConnection.sendMessageToClient((byte)'T');
            myClientConnection.sendMessageToClient((byte)'h');
            myClientConnection.sendMessageToClient((byte)'e');
            myClientConnection.sendMessageToClient((byte)' ');
            myClientConnection.sendMessageToClient((byte)'t');
            myClientConnection.sendMessageToClient((byte)'i');
            myClientConnection.sendMessageToClient((byte)'m');
            myClientConnection.sendMessageToClient((byte)'e');
            myClientConnection.sendMessageToClient((byte)' ');
            myClientConnection.sendMessageToClient((byte)'i');
            myClientConnection.sendMessageToClient((byte)'s');
            myClientConnection.sendMessageToClient((byte)':');
            myClientConnection.sendMessageToClient((byte)' ');
            for (int i = 0; i < sdf.format(cal.getTime()).length(); i++) {
                msg = (byte) sdf.format(cal.getTime()).charAt(i);
                myClientConnection.sendMessageToClient(msg);
            }
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.update("Client has requested time: "+sdf.format(cal.getTime()));
        }
        else if (theCommand.contains("temp")) {
            if(mx7Client.isConnected()){
                mx7Client.sendMessageToServer((byte) 't');
                mx7Client.terminateMessage();
            }
        } 
    }
}
