#include "TCPIPConfig.h"

#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)

#include "TCPIP Stack/TCPIP.h"
#include <ctype.h>
#include <stdio.h>

// Defines which port the server will listen on
#define TCP_LED_PB_CONSOLE_SERVER_PORT    8888

//Function Prototype
extern void LED_PB_CONFIG();
extern void LED_ON();
extern void LED_OFF();
extern int READ_PB();
extern float getTemp();

static enum _myState {
    SM_OPEN_SERVER_SOCKET = 0,
    SM_LISTEN_FOR_CLIENT_CONNECTION,
    SM_DISPLAY_INTRO_MESSAGE,
    SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE,
    SM_PROCESS_COMMAND,
    SM_TERMINATE_MESSAGE,
    SM_DISCONNECT_CLIENT,
    SM_QUIT_CLIENT
} myState = SM_OPEN_SERVER_SOCKET;

/*****************************************************************************
  Function:
        void TCP_LED_PB_CONSOLE_Server(void)

  Summary:
        Implements a simple TCP Server, which provides access to the
        Mx7 development board LEDs and can read the status of the PBs
        using a TCP connection and a Telnet client.

  Description:
        Using the model TCP server provided, this server allows the control
        of the MX7 on-board LEDs and can read the status of the PBs over a TCP
        connection.

  Precondition:
        TCP is initialized.

  Parameters:
        None

  Returns:
        None
 ***************************************************************************/
void TCP_LED_PB_CONSOLE_Server(void) {
    static TCP_SOCKET mySocket;
    WORD numBytes = 0;
    BYTE inputChar = 0;
    static char command[10];
    static int commandIndex = 0;
    static int connected = 0;
    static int disconnectClient = 0;
    int size;
    int PBStatus;

    switch (myState) {
        case SM_OPEN_SERVER_SOCKET:
            mySocket = TCPOpen(0, TCP_OPEN_SERVER, TCP_LED_PB_CONSOLE_SERVER_PORT, TCP_PURPOSE_TCP_LED_PB_CONSOLE_SERVER);
            if (mySocket == INVALID_SOCKET)
                return;
            myState = SM_LISTEN_FOR_CLIENT_CONNECTION;
            break;

        case SM_LISTEN_FOR_CLIENT_CONNECTION:
            if (TCPIsConnected(mySocket) == FALSE)
                return;
            else {
                LED_PB_CONFIG();
                connected = 1;
                myState = SM_DISPLAY_INTRO_MESSAGE;
                break;
            }
        case SM_DISPLAY_INTRO_MESSAGE: // Display the menu
            if (!TCPIsConnected(mySocket)){
                myState = SM_DISCONNECT_CLIENT;
                connected = 0;
                return;
            }

            int size = sizeof ("Connected to the MX7 Server.");

            if ((TCPIsPutReady(mySocket)) < size)
                return;

            TCPPutArray(mySocket, (BYTE*) "Connected to the MX7 Server.", size);

            TCPFlush(mySocket);

            myState = SM_TERMINATE_MESSAGE;

            break;

        case SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE:
            if (TCPIsConnected(mySocket) == FALSE) {
                myState = SM_DISCONNECT_CLIENT;
                connected = 0;
                return;
            }
            if (TCPIsGetReady(mySocket < 1))
                return;
            TCPGet(mySocket, &inputChar);
            if (inputChar == (BYTE) 0xFF) {
                commandIndex = 0;
                myState = SM_PROCESS_COMMAND;
            } else if (inputChar != 0) {
                command[commandIndex] = inputChar;
                commandIndex++;
            }
            break;

        case SM_PROCESS_COMMAND:
            if (command[0] == 'd') {
                myState = SM_DISCONNECT_CLIENT;
                connected = 0;
                return;
            }
            if (command[0] == 'q') {
                myState = SM_QUIT_CLIENT;
                connected = 0;
                return;
            }
            
            if(command[0] == 't'){
                float temp = getTemp();
                
                char floatToString[7];
                
                sprintf(floatToString, "%f",(double) temp);
                
                size = sizeof (floatToString);
                
                if ((TCPIsPutReady(mySocket)) < size)
                    return;

                TCPPutArray(mySocket, (BYTE*) floatToString, size);

                TCPFlush(mySocket);
                
                myState = SM_TERMINATE_MESSAGE;
                break;
                
            }       
            
            if (command[0] == 'L') {
                switch (command[1]) {
                    case '1':
                        if (command[3] == 'n') {
                            size = sizeof ("LED1 on.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED1 on.", size);

                            TCPFlush(mySocket);

                            LED_ON(1);

                            break;
                        } else {
                            size = sizeof ("LED1 off.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED1 off.", size);

                            TCPFlush(mySocket);

                            LED_OFF(1);

                            break;

                        }

                    case '2':
                        if (command[3] == 'n') {

                            size = sizeof ("LED2 on.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED2 on.", size);

                            TCPFlush(mySocket);

                            LED_ON(2);

                            break;
                        } else {
                            size = sizeof ("LED2 off.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED2 off.", size);

                            TCPFlush(mySocket);

                            LED_OFF(2);

                            break;
                        }

                    case '3':
                        if (command[3] == 'n') {

                            size = sizeof ("LED3 on.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED3 on.", size);

                            TCPFlush(mySocket);

                            LED_ON(3);

                            break;
                        } else {
                            size = sizeof ("LED3 off.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED3 off.", size);

                            TCPFlush(mySocket);

                            LED_OFF(3);

                            break;
                        }

                    case '4':
                        if (command[3] == 'n') {

                            size = sizeof ("LED4 on.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED4 on.", size);

                            TCPFlush(mySocket);

                            LED_ON(4);

                            break;
                        } else {
                            size = sizeof ("LED4 off.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "LED4 off.", size);

                            TCPFlush(mySocket);

                            LED_OFF(4);

                            break;
                        }
                        break;
                }
                myState = SM_TERMINATE_MESSAGE;
                break;
            }

            if (command[0] == 'g') {
                switch (command[3]) {
                    case '1':
                        PBStatus = READ_PB(1);

                        if (PBStatus == 0) {
                            int size = sizeof ("PB1 up.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB1 up.", size);

                            TCPFlush(mySocket);
                        } else {
                            int size = sizeof ("PB1 down.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB1 down.", size);

                            TCPFlush(mySocket);
                        }

                        break;

                    case '2':
                        PBStatus = READ_PB(2);

                        if (PBStatus == 0) {
                            int size = sizeof ("PB2 up.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB2 up.", size);

                            TCPFlush(mySocket);
                        } else {
                            int size = sizeof ("PB2 down.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB2 down.", size);

                            TCPFlush(mySocket);
                        }

                        break;

                    case '3':
                        PBStatus = READ_PB(3);

                        if (PBStatus == 0) {
                            int size = sizeof ("PB3 up.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB3 up.", size);

                            TCPFlush(mySocket);
                        } else {
                            int size = sizeof ("PB3 down.");

                            if ((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*) "PB3 down.", size);

                            TCPFlush(mySocket);
                        }
                        break;
                }
                myState = SM_TERMINATE_MESSAGE;
                break;
            }
            myState = SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE;
            break;

        case SM_TERMINATE_MESSAGE:
            //Append 0xFF to terminate message

            if ((TCPIsPutReady(mySocket)) < 1)
                return;

            TCPPut(mySocket, 0xFF);

            TCPFlush(mySocket);
            
            if (disconnectClient == 1){
                TCPDisconnect(mySocket);
                connected = 0;
                disconnectClient = 0;
            }
            if (connected == 1)
                myState = SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE;
            else if (connected == 0)
                myState = SM_LISTEN_FOR_CLIENT_CONNECTION;
            break;

        case SM_DISCONNECT_CLIENT:
            size = sizeof ("Disconnect Ack");

            if ((TCPIsPutReady(mySocket)) < size)
                return;

            TCPPutArray(mySocket, (BYTE*) "Disconnect Ack", size);

            TCPFlush(mySocket);
            
            disconnectClient = 1;

            myState = SM_TERMINATE_MESSAGE;
            
            break;
            
        case SM_QUIT_CLIENT:
            size = sizeof ("Quit Ack");

            if ((TCPIsPutReady(mySocket)) < size)
                return;

            TCPPutArray(mySocket, (BYTE*) "Quit Ack", size);

            TCPFlush(mySocket);

            disconnectClient = 1;

            myState = SM_TERMINATE_MESSAGE;

            break;
            
    }
}

#endif //#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)
