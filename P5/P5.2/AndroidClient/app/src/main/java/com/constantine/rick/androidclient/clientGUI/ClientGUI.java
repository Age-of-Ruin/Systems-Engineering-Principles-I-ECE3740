package com.constantine.rick.androidclient.clientGUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.constantine.rick.androidclient.R;

public class ClientGUI extends AppCompatActivity {

    com.constantine.rick.androidclient.client.Client myClient;
    com.constantine.rick.androidclient.usercommandhandler.UserCommandHandler myUserCommandHandler;
    TextView myConsole;
    EditText myPortTextBox;
    EditText myIPTextBox;
    boolean LED1ON = false; boolean LED2ON = false; boolean LED3ON = false; boolean LED4ON = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myClient = new com.constantine.rick.androidclient.client.Client("10.0.2.2", 7777, this);
        myUserCommandHandler = new com.constantine.rick.androidclient.usercommandhandler.UserCommandHandler(this, myClient);
        myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myPortTextBox = findViewById(R.id.PORT_NUMBER_TEXT_FIELD);
        myIPTextBox = findViewById(R.id.IP_ADDRESS_TEXT_FIELD);
    }

    public void update(String theString) {
        Message msg = new Message();
        msg.obj = theString;
        msg.setTarget(myServerMessageTextBoxHandler);
        myServerMessageTextBoxHandler.sendMessage(msg);
    }

    //Handler needed to send messages to UI (outside threads cannot access main UI thread)
    @SuppressLint("HandlerLeak")
    Handler myServerMessageTextBoxHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            myConsole.setText((CharSequence) msg.obj);
        }
    };


    public void connectButtonHandler(View view) {
        myUserCommandHandler.execute("Connect");
    }

    public void disconnectButtonHandler(View view) {
        myUserCommandHandler.execute("Disconnect");
    }

    public void getTimeButtonHandler(View view) {
        myUserCommandHandler.execute("Get Time");
    }

    public void getTempButtonHandler(View view) {
        myUserCommandHandler.execute("Get Temp");
    }

    public void quitButtonHandler(View view) {
        myUserCommandHandler.execute("Quit");
    }

    public void portNumberButtonHandler(View view) {
        String myPort = myPortTextBox.getText().toString();

        myUserCommandHandler.execute("New Port " + myPort);
    }

    public void IPButtonHandler(View view) {
        String myIP = myIPTextBox.getText().toString();

        myUserCommandHandler.execute("New IP " + myIP);
    }

    public void LED1ButtonHandler(View view) {
        if(LED1ON == false) {
            myUserCommandHandler.execute("L1ON");
            LED1ON = true;
        }
        else{
            myUserCommandHandler.execute("L1OFF");
            LED1ON = false;
        }
    }

    public void LED2ButtonHandler(View view) {
        if(LED2ON == false) {
            myUserCommandHandler.execute("L2ON");
            LED2ON = true;
        }
        else{
            myUserCommandHandler.execute("L2OFF");
            LED2ON = false;
        }
    }

    public void LED3ButtonHandler(View view) {
        if(LED3ON == false) {
            myUserCommandHandler.execute("L3ON");
            LED3ON = true;
        }
        else{
            myUserCommandHandler.execute("L3OFF");
            LED3ON = false;
        }
    }

    public void LED4ButtonHandler(View view) {
        if(LED4ON == false) {
            myUserCommandHandler.execute("L4ON");
            LED4ON = true;
        }
        else{
            myUserCommandHandler.execute("L4OFF");
            LED4ON = false;
        }
    }

    public void PB1ButtonHandler(View view) {
        myUserCommandHandler.execute("GPB1");
    }

    public void PB2ButtonHandler(View view) {
        myUserCommandHandler.execute("GPB2");
    }

    public void PB3ButtonHandler(View view) {
        myUserCommandHandler.execute("GPB3");
    }
}
