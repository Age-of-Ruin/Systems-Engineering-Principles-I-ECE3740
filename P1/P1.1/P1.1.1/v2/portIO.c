#include "PortConfig.h"

void portInit(void){
    PORTSetPinsDigitalOut(LED1_IOPORT, LED1_BIT); //Set the pin connected to LED1 as output
    PORTSetPinsDigitalOut(LED2_IOPORT, LED2_BIT); //Set the pin connected to LED2 as output
    PORTSetPinsDigitalOut(LED3_IOPORT, LED3_BIT); //Set the pin connected to LED3 as output
    PORTSetPinsDigitalOut(LED4_IOPORT, LED4_BIT); //Set the pin connected to LED4 as output

    PORTClearBits(LED1_IOPORT, LED1_BIT); // LED1 off
    PORTClearBits(LED2_IOPORT, LED2_BIT); // LED2 off
    PORTClearBits(LED3_IOPORT, LED3_BIT); // LED3 off
    PORTClearBits(LED4_IOPORT, LED4_BIT); // LED4 off
}

void ledBlink(int portSelect, int delay){
           
    unsigned int i;
    
    switch(portSelect){
        case 1:
        PORTSetBits(LED1_IOPORT, LED1_BIT); // LED1 on
            for (i = 0; i < delay; i++); // Delay some time
        PORTClearBits(LED1_IOPORT, LED1_BIT); // LED1 off
        break;
        
        case 2:
        PORTSetBits(LED2_IOPORT, LED2_BIT); // LED2 on
            for (i = 0; i < delay; i++); // Delay some time
        PORTClearBits(LED2_IOPORT, LED2_BIT); // LED1 off
        break;
        
        case 3:
        PORTSetBits(LED3_IOPORT, LED3_BIT); // LED3 on
            for (i = 0; i < delay; i++); // Delay some time
        PORTClearBits(LED3_IOPORT, LED3_BIT); // LED1 off    
        break;
        
        case 4:
        PORTSetBits(LED4_IOPORT, LED4_BIT); // LED4 on
            for (i = 0; i < delay; i++); // Delay some time
        PORTClearBits(LED4_IOPORT, LED4_BIT); // LED4 off
        break;    
    }
    
}