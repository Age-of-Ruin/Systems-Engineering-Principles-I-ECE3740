#include <plib.h>
#include "LED_PB_CONFIG.h"

void LED_OFF(int LEDSelect){
    switch (LEDSelect){
        case 1:
            PORTClearBits(LED1_IOPORT, LED1_BIT); // LED1 off 
            break;
        case 2:
            PORTClearBits(LED2_IOPORT, LED2_BIT); // LED1 off 
            break;
        case 3:
            PORTClearBits(LED3_IOPORT, LED3_BIT); // LED1 off 
            break;
        case 4:
            PORTClearBits(LED4_IOPORT, LED4_BIT); // LED1 off 
            break;  
    }   
}
