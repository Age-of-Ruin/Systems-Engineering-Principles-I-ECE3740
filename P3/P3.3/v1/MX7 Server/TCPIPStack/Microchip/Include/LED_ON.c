#include <plib.h>
#include "LED_PB_CONFIG.h"

void LED_ON(int LEDSelect){
    switch (LEDSelect){
        case 1:
            PORTSetBits(LED1_IOPORT, LED1_BIT); // LED1 on
            break;
        case 2:
            PORTSetBits(LED2_IOPORT, LED2_BIT); // LED1 on
            break;
        case 3:
            PORTSetBits(LED3_IOPORT, LED3_BIT); // LED1 on
            break;
        case 4:
            PORTSetBits(LED4_IOPORT, LED4_BIT); // LED1 on
            break;  
    }   
}