#include <plib.h>
#include "SysConfig.h"

void initSystem(void){
    SYSTEMConfig(SYS_FREQ, SYS_CFG_ALL); //See <system.h>
}
