#include "TCPIPConfig.h"

#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)

#include "TCPIP Stack/TCPIP.h"
#include <ctype.h>

// Defines which port the server will listen on
#define TCP_LED_PB_CONSOLE_SERVER_PORT    7777

static enum _myState {
    SM_OPEN_SERVER_SOCKET = 0,
    SM_LISTEN_FOR_CLIENT_CONNECTION,
    SM_DISPLAY_MENU,
    SM_PROCESS_COMMAND,
    SM_DISCONNECT_CLIENT
} myState = SM_OPEN_SERVER_SOCKET;

static enum _commandEnums {
    DO_NO_COMMAND = 0,
    DO_QUIT,
    SERVICE_CLIENT,
} myCommand = DO_NO_COMMAND;

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
                myState = SM_DISPLAY_MENU;
                break;
            }
        case SM_DISPLAY_MENU: // Display the menu
            if(!TCPIsConnected(mySocket)) 
                return;
            
            int size = sizeof("\x1B[2J \x1B[1m    LED SERVER MENU\n\r"
                              "1: LED0 ON\t2: LED0 OFF\n\r3: LED1 ON\t"
                              "4: LED1 OFF\n\r5: LED2 ON\t6: LED2 OFF\n\r"
                              "7: LED3 ON\t8: LED3 OFF\n\r"
                              "a: PB0  s: PB1  d: PB2\n\r");

            if((TCPIsPutReady(mySocket)) < size)
                return;

            TCPPutArray(mySocket,(BYTE*)"\x1B[2J \x1B[1m    LED SERVER MENU\n\r"
                                        "1: LED0 ON\t2: LED0 OFF\n\r3: LED1 ON\t"
                                        "4: LED1 OFF\n\r5: LED2 ON\t6: LED2 OFF\n\r"
                                        "7: LED3 ON\t8: LED3 OFF\n\r"
                                        "a: PB0  s: PB1  d: PB2\n\n\r", size);

            TCPFlush(mySocket);

            myState = SM_PROCESS_COMMAND;

            break;
        case SM_PROCESS_COMMAND:
            if (TCPIsConnected(mySocket) == FALSE) {
                myState = SM_DISCONNECT_CLIENT;
                return;
            }
            if (TCPIsPutReady(mySocket) < (WORD) 1)
                return;
            if ((numBytes = TCPIsGetReady(mySocket)) == 0)
                myCommand = DO_NO_COMMAND;
            else
                TCPGet(mySocket, &inputChar);
                if (inputChar == 'q')
                    myCommand = DO_QUIT;
                else
                    myCommand = SERVICE_CLIENT;
            switch (myCommand) {
                case DO_NO_COMMAND:
                    break;
                case DO_QUIT:
                    myState = SM_DISCONNECT_CLIENT;
                    break;
                case SERVICE_CLIENT:
                    if (inputChar == '1'){
                        
                        int size = sizeof("\x1B[1K \rLED0 on \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED0 on \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '2'){
                        
                        int size = sizeof("\x1B[1K \rLED0 off \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED0 off \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '3'){
                        
                        int size = sizeof("\x1B[1K \rLED1 on \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED1 on \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '4'){
                        
                        int size = sizeof("\x1B[1K \rLED1 off \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED1 off \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '5'){
                        
                        int size = sizeof("\x1B[1K \rLED2 on \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED2 on \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '6'){
                        
                        int size = sizeof("\x1B[1K \rLED2 off \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED2 off \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '7'){
                        
                        int size = sizeof("\x1B[1K \rLED3 on \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED3 on \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == '8'){
                        
                        int size = sizeof("\x1B[1K \rLED3 off \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rLED3 off \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == 'a'){
                        
                        int size = sizeof("\x1B[1K \rPBa \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rPBa \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == 's'){
                        
                        int size = sizeof("\x1B[1K \rPBb \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rPBb \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }
                    if (inputChar == 'd'){
                        
                        int size = sizeof("\x1B[1K \rPBc \x1B[K");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"\x1B[1K \rPBc \x1B[K", size);

                        TCPFlush(mySocket);
                        break;
                    }    
            }
            break;
        case SM_DISCONNECT_CLIENT:
            TCPDisconnect(mySocket);
            myState = SM_LISTEN_FOR_CLIENT_CONNECTION;
            break;
    }
}

#endif //#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)
