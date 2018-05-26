package main

import (
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"encoding/json"
	"fmt"
)

type SmartContract struct {
}

type balanceChange struct {
	InstitutionID string `json:"institution_id"`
	UserID string `json:"user_id"`
	DateTime string `json:"date_time"`
	Type string `json:"type"`
	Sum string `json:"sum"`// negative for withdraw, positive for top
}

func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)

}

func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {
	function, args := APIstub.GetFunctionAndParameters();
	if function == "queryBalanceChange" {
		return s.queryBalanceChange(APIstub,args)
	}else if function == "createBalanceChange"{
		return s.createBalanceChange(APIstub,args)
	}else if function == "initLedger" {
		return s.initLedger(APIstub)
	}
	return shim.Success(nil)
}

func (s *SmartContract) queryBalanceChange (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	changeAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(changeAsBytes)
}

func (s *SmartContract) createBalanceChange (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 6 {
		return shim.Error("Incorrect number of arguments. Expecting 6")
	}
	var change = balanceChange{InstitutionID:args[1],UserID:args[2],DateTime:args[3],Type:args[4],Sum:args[5]}
	fmt.Println(change)
	changeAsBytes, _ := json.Marshal(change)
	APIstub.PutState(args[0],changeAsBytes)
	return shim.Success(nil)
}

func (s *SmartContract) initLedger (APIstub shim.ChaincodeStubInterface) sc.Response  {
	return shim.Success(nil)
}

func main()  {
	err := shim.Start(new(SmartContract))
	if err != nil {
		fmt.Printf("Error creating new Smart Contract: %s",err)
	}
}
