package clienttest;

public class clienttest {
      
    public static void main(String[] args) {
   
//***************** GUI ******************************
    clientGUI.ClientGUI myUI = new clientGUI.ClientGUI();
        /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            myUI.setVisible(true);
        }
    });
    
    client.Client myClient = new client.Client("192.168.1.55", 5555, myUI);
    myUI.setClient(myClient);
    myUI.update("Hello, please select a command from the menu.");
    
        //************** Standard IO**********************
//    standardiouserinterface.StandardIO myUI = new standardiouserinterface.StandardIO();
//    client.Client myClient = new client.Client(null, 5555, myUI);
//    usercommandhandler.UserCommandHandler myUserCommandHandler = new usercommandhandler.UserCommandHandler(myUI, myClient);
//    myUI.setCommandHandler(myUserCommandHandler);
//    Thread theUIThread = new Thread(myUI);
//    theUIThread.start();
//    myUI.update("1:\tCreate Socket/Thread & Connect\n2:\tDisconnect and Stop"
//                + "Socket/Thread\n3:\tGet Time\n4:\tQuit\n\nEnter command");
        
    }//main

}
