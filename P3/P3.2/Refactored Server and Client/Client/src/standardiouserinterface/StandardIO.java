package standardiouserinterface;

import java.io.*;

public class StandardIO implements Runnable, userinterface.UserInterface {

    BufferedReader console = null;
    usercommandhandler.UserCommandHandler myUserCommandHandler;

    public StandardIO() {
        console = new BufferedReader(new InputStreamReader(System.in));
        if (console == null) {
            System.err.println("No Standard Input device, exiting program.");
            System.exit(1);
        }
    }
    
    public void setCommandHandler(usercommandhandler.UserCommandHandler theUserCommandHandler) {
        this.myUserCommandHandler = theUserCommandHandler;
    }

    public String getUserInput() {
        String userInput = "no input";

        try {
            userInput = console.readLine();
            return userInput;
        } catch (IOException e) {
            System.err.println("Error reading from Standard Input device, exiting program.");
            System.exit(1);
        }
        return userInput;
    }
    
    @Override
    public void update(String theMessage){
        System.out.println(theMessage);
    }

    @Override
    public void run() {
        while (true) {
            String userInput = getUserInput();
            myUserCommandHandler.execute(userInput);
        }
    }
}
