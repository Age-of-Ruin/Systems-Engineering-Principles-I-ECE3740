#include <xc.h>
#include <plib.h>

// Configuration Bits
#pragma config FNOSC    = PRIPLL        // Oscillator Selection
#pragma config FPLLIDIV = DIV_2         // PLL Input Divider (PIC32 Starter Kit: use divide by 2 only)
#pragma config FPLLMUL  = MUL_20        // PLL Multiplier
#pragma config FPLLODIV = DIV_1         // PLL Output Divider
#pragma config FPBDIV   = DIV_1         // Peripheral Clock divisor
#pragma config FWDTEN   = OFF           // Watchdog Timer
#pragma config WDTPS    = PS1           // Watchdog Timer Postscale
#pragma config FCKSM    = CSDCMD        // Clock Switching & Fail Safe Clock Monitor
#pragma config OSCIOFNC = OFF           // CLKO Enable
#pragma config POSCMOD  = XT            // Primary Oscillator
#pragma config IESO     = OFF           // Internal/External Switch-over
#pragma config FSOSCEN  = OFF           // Secondary Oscillator Enable
#pragma config CP       = OFF           // Code Protect
#pragma config BWP      = OFF           // Boot Flash Write Protect
#pragma config PWP      = OFF           // Program Flash Write Protect
#pragma config DEBUG    = ON            // Debugger Enabled
#pragma config ICESEL   = ICS_PGx1		// As required by Page 3 of 34: Cerebot_MX7cK_rm.pdf

#define SYS_FREQ		(80000000)

// IOPORT bit masks can be found in ports.h
#define LED1_IOPORT	IOPORT_G
#define	LED1_BIT	BIT_12
#define LED2_IOPORT	IOPORT_G
#define	LED2_BIT	BIT_13
#define LED3_IOPORT	IOPORT_G
#define	LED3_BIT	BIT_14
#define LED4_IOPORT	IOPORT_G
#define	LED4_BIT	BIT_15

#define PB1_IOPORT  IOPORT_G
#define PB1_BIT     BIT_6     
#define PB2_IOPORT  IOPORT_G
#define PB2_BIT     BIT_7
#define PB3_IOPORT  IOPORT_A
#define PB3_BIT     BIT_0

void PB_LED_CONFIG(void) {
    SYSTEMConfig(SYS_FREQ, SYS_CFG_ALL); //See <system.h>

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
    
    PORTSetBits(LED1_IOPORT, LED1_BIT); // LED1 on
}






//    while (1) {
//    
//        unsigned int readPB1;
//        unsigned int readPB2;
//        unsigned int readPB3;
//        
//        readPB1 = PORTReadBits(PB1_IOPORT, PB1_BIT);
//        readPB2 = PORTReadBits(PB2_IOPORT, PB2_BIT);
//        readPB3 = PORTReadBits(PB3_IOPORT, PB3_BIT);
//        
//        if(readPB1 != 0){
//            PORTSetBits(LED1_IOPORT, LED1_BIT); // LED1 on
//        }
//        else{
//            PORTClearBits(LED1_IOPORT, LED1_BIT); // LED1 off 
//        }
//        
//        if(readPB2 != 0){
//            PORTSetBits(LED2_IOPORT, LED2_BIT); // LED1 on
//        }
//        else{
//            PORTClearBits(LED2_IOPORT, LED2_BIT); // LED1 off 
//        }
//        
//        if(readPB3 != 0){
//            PORTSetBits(LED3_IOPORT, LED3_BIT); // LED1 on
//        }
//        else{
//            PORTClearBits(LED3_IOPORT, LED3_BIT); // LED1 off 
//        }
//    }

