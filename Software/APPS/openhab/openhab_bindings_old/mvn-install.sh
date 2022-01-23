#! /bin/bash

mvn install -X -Dpmd.skip=true -Dcheckstyle.skip=true -Dreport.fail.on.error=false
