#include <plib.h>
#include "LED_PB_CONFIG.h"

int READ_PB(int PBSelect){
    unsigned int readPB;
    int result = 0;
    
    switch (PBSelect){
        case 1:
            readPB = PORTReadBits(PB1_IOPORT, PB1_BIT);
            
            if(readPB != 0)
                result = 1;

            return result;       
            
        case 2:
            readPB = PORTReadBits(PB2_IOPORT, PB2_BIT);
            
            if(readPB != 0)
                result = 1;

            return result;   
            
        case 3:
            readPB = PORTReadBits(PB3_IOPORT, PB3_BIT);
            
            if(readPB != 0)
                result = 1;

            return result;   
    }
    
    return result;
}
