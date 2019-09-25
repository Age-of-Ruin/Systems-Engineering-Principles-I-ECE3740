#include "TCPIPConfig.h"

#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)

#include "TCPIP Stack/TCPIP.h"
#include <ctype.h>

// Defines which port the server will listen on
#define TCP_LED_PB_CONSOLE_SERVER_PORT    5555

//Function Prototype
extern void LED_PB_CONFIG();
extern void LED_ON();
extern void LED_OFF();
extern int READ_PB();

static enum _myState {
    SM_OPEN_SERVER_SOCKET = 0,
    SM_LISTEN_FOR_CLIENT_CONNECTION,
    SM_DISPLAY_MENU,
    SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE,
    SM_PROCESS_COMMAND,
    SM_TERMINATE_MESSAGE,
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
    int command[10];
    int commandIndex = 0;
    char theChar;
    
    
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
                myState = SM_DISPLAY_MENU;
                break;
            }
        case SM_DISPLAY_MENU: // Display the menu
            if(!TCPIsConnected(mySocket)) 
                myState = SM_DISCONNECT_CLIENT;
            
            int size = sizeof("Connected to the MX7 Server.");

            if((TCPIsPutReady(mySocket)) < size)
                return;

            TCPPutArray(mySocket,(BYTE*)"Connected to the MX7 Server.", size);

            TCPFlush(mySocket);

            myState = SM_PROCESS_COMMAND;

            break;
            
//        case SM_WAIT_FOR_COMPLETE_CLIENT_MESSAGE:
//            if(TCPIsConnected(mySocket) == FALSE){
//                myState = SM_DISCONNECT_CLIENT;
//                return;
//            }
//            if(TCPIsGetReady(mySocket<1))
//                return;
//            TCPGet(mySocket, &theChar);
//            if(theChar == (BYTE)0xFF){
//                commandIndex = 0;
//                myState = SM_PROCESS_COMMAND;                
//            }
//            else{
//                command[commandIndex] = theChar;
//                commandIndex++;
//            }
//            break;
            
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
                        
                        int size = sizeof("LED1 on.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED1 on.", size);

                        TCPFlush(mySocket);
                        
                        LED_ON(1);
                        
                        break;
                    }
                    if (inputChar == '2'){
                        
                        int size = sizeof("LED1 off.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED1 off.", size);

                        TCPFlush(mySocket);
                        
                        LED_OFF(1);
                        
                        break;
                    }
                    if (inputChar == '3'){
                        
                        int size = sizeof("LED2 on.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED2 on.", size);

                        TCPFlush(mySocket);
                        
                        LED_ON(2);
                        
                        break;
                    }
                    if (inputChar == '4'){
                        
                        int size = sizeof("LED2 off.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED2 off.", size);

                        TCPFlush(mySocket);
                        
                        LED_OFF(2);
                        
                        break;
                    }
                    if (inputChar == '5'){
                        
                        int size = sizeof("LED3 on.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED3 on.", size);

                        TCPFlush(mySocket);
                        
                        LED_ON(3);
                        
                        break;
                    }
                    if (inputChar == '6'){
                        
                        int size = sizeof("LED3 off.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED3 off.", size);

                        TCPFlush(mySocket);
                        
                        LED_OFF(3);
                        
                        break;
                    }
                    if (inputChar == '7'){
                        
                        int size = sizeof("LED4 on.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED4 on.", size);

                        TCPFlush(mySocket);
                        
                        LED_ON(4);
                        
                        break;
                    }
                    if (inputChar == '8'){
                        
                        int size = sizeof("LED4 off.");

                        if((TCPIsPutReady(mySocket)) < size)
                            return;

                        TCPPutArray(mySocket, (BYTE*)"LED4 off.", size);

                        TCPFlush(mySocket);
                        
                        LED_OFF(4);
                        
                        break;
                    }
                    if (inputChar == 'a'){
                        
                        int PBStatus = READ_PB(1);

                        if(PBStatus == 0){
                            int size = sizeof("PB1 up.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB1 up.", size);

                            TCPFlush(mySocket);
                        }
                        else{
                            int size = sizeof("PB1 down.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB1 down.", size);

                            TCPFlush(mySocket);
                        }
                        
                        break;
                    }
                    if (inputChar == 's'){
                        
                        int PBStatus = READ_PB(2);
                        
                        if(PBStatus == 0){
                            int size = sizeof("PB2 up.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB2 up.", size);

                            TCPFlush(mySocket);
                        }
                        else{
                            int size = sizeof("PB2 down.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB2 down.", size);

                            TCPFlush(mySocket);
                        }
                        
                        break;
                    }
                    if (inputChar == 'd'){
                        
                        int PBStatus = READ_PB(3);
                        
                        if(PBStatus == 0){
                            int size = sizeof("PB3 up.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB3 up.", size);

                            TCPFlush(mySocket);
                        }
                        else{
                            int size = sizeof("PB3 down.");

                            if((TCPIsPutReady(mySocket)) < size)
                                return;

                            TCPPutArray(mySocket, (BYTE*)"PB3 down.", size);

                            TCPFlush(mySocket);
                        }
                        
                        break;
                    }    
            }
            myState = SM_TERMINATE_MESSAGE;
            break;
            
        case SM_TERMINATE_MESSAGE:
            //Append 0xFF to terminate message
   
            if((TCPIsPutReady(mySocket)) < 1)
                return;

            TCPPut(mySocket, 0xFF);

            TCPFlush(mySocket);  
            
            myState = SM_PROCESS_COMMAND;
            break;
   
        case SM_DISCONNECT_CLIENT:
            TCPDisconnect(mySocket);
            myState = SM_LISTEN_FOR_CLIENT_CONNECTION;
            break;
    }
}

#endif //#if defined(STACK_USE_TCP_LED_PB_CONSOLE_SERVER)
