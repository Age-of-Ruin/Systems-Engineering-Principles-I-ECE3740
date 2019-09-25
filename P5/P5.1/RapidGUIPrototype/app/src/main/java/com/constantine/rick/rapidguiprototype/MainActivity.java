package com.constantine.rick.rapidguiprototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void portNumberButtonHandler(View view) {
        EditText myPortTextBox;
        String myPort;
        myPortTextBox = findViewById(R.id.PORT_NUMBER_TEXT_FIELD);
        myPort = myPortTextBox.getText().toString();

        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myConsole.setText(myPort);
    }

    public void IPButtonHandler(View view) {
        //Get IP address as string
        EditText myIPTextBox;
        String myIP;
        myIPTextBox = findViewById(R.id.IP_ADDRESS_TEXT_FIELD);
        myIP = myIPTextBox.getText().toString();

        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myConsole.setText(myIP);
}

    public void connectButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myConsole.setText("Connected.");
    }

    public void disconnectButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myConsole.setText("Disconnected.");
    }

    public void LED1ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("LED 1 on");
        else
            myConsole.setText("LED 1 off");
    }

    public void LED2ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("LED 2 on");
        else
            myConsole.setText("LED 2 off");
    }

    public void LED3ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("LED 3 on");
        else
            myConsole.setText("LED 3 off");
    }

    public void LED4ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("LED 4 on");
        else
            myConsole.setText("LED 4 off");
    }

    public void PB1ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("PB 1 up");
        else
            myConsole.setText("PB 1 down");
    }

    public void PB2ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("PB 2 up");
        else
            myConsole.setText("PB 2 down");
    }

    public void PB3ButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        if(Math.random() < .5 )
            myConsole.setText("PB 3 up");
        else
            myConsole.setText("PB 3 down");
    }

    public void getTempButtonHandler(View view) {
        //Update Console
        TextView myConsole = findViewById(R.id.SERVER_MESSAGE_TEXT_FIELD);
        myConsole.setText("Cannot read temperature - only prototype GUI");
    }
}
