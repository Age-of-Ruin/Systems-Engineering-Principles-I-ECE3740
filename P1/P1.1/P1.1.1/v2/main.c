#include <xc.h>
#include <plib.h>

#include "PICconfig.h"
#include "PortConfig.h"
#include "SysConfig.h"

int main(void) {
    
    initSystem();
    portInit();

    while (1) {
        ledBlink(1,1000000);
        ledBlink(2,1000000);
        ledBlink(3,1000000);
        ledBlink(4,1000000);
    }
}

