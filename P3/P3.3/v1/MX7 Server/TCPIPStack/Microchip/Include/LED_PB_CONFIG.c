#include <plib.h>
#include "LED_PB_CONFIG.h"

void LED_PB_CONFIG(void) {
    
    PORTSetPinsDigitalOut(LED1_IOPORT, LED1_BIT); //Set the pin connected to LED1 as output
    PORTSetPinsDigitalOut(LED2_IOPORT, LED2_BIT); //Set the pin connected to LED2 as output
    PORTSetPinsDigitalOut(LED3_IOPORT, LED3_BIT); //Set the pin connected to LED3 as output
    PORTSetPinsDigitalOut(LED4_IOPORT, LED4_BIT); //Set the pin connected to LED4 as output

    PORTSetPinsDigitalIn(PB1_IOPORT, PB1_BIT);
    PORTSetPinsDigitalIn(PB2_IOPORT, PB2_BIT);
    PORTSetPinsDigitalIn(PB3_IOPORT, PB3_BIT);
    
    PORTClearBits(LED1_IOPORT, LED1_BIT); // LED1 off
    PORTClearBits(LED2_IOPORT, LED2_BIT); // LED2 off
    PORTClearBits(LED3_IOPORT, LED3_BIT); // LED3 off
    PORTClearBits(LED4_IOPORT, LED4_BIT); // LED4 off
    
    DDPCONbits.JTAGEN = 0;
}
    
