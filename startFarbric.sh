#!/bin/sh

#  startFarbric.sh
#  
#  Copyright LW-Ricarido(李威) All Rights Reserved
#  Created by 李威 on 2018/5/26.
#  SPDX-License-Identifier: Apache-2.0
#  Exit on first error
set -e
export MSYS_NO_PATHCONV = 1
#chaincode path for transaction
CC_TA_PATH=github.com/transaction
#chaincode path for balanceChange
CC_BC_PATH = github.com/balanceChange

#clean the keystore
rm -rf ./hfc-key-store

# launch network; create channel and join peer to channel
cd basic-network
./start.sh
# Now launch the CLI container in order to install, instantiate chaincode
docker-compose -f ./docker-compose.yml up -d cli

docker-compose -f

