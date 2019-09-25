package servertest;

public class ServerTest {

    public static void main(String[] args) {

        standardio.StandardIO myUI = new standardio.StandardIO();
        mx7serverconnection.Client mx7Client = new mx7serverconnection.Client("192.168.1.55", 8888, myUI);
        clientcommandhandler.ClientCommandHandler myClientCommand = new clientcommandhandler.ClientCommandHandler(myUI, mx7Client);
        server.Server myServer = new server.Server(7777, 1, myClientCommand, myUI, mx7Client);
        usercommandhandler.UserCommandHandler myCommand = new usercommandhandler.UserCommandHandler(myUI, myServer, mx7Client);
        myUI.setCommand(myCommand);
        Thread myUIthread = new Thread(myUI);
        myUIthread.start();     
        myUI.update("1: Start Server Socket\n2: Listen\n3: Connect to MX7 Server\n"
                + "4: Disconnect from MX7 Server\n5: Get Temp (Test)\n6: Quit");
    }
}
