package chaincode


import (
	"encoding/json"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"fmt"
)

type SmartContract struct {
}

type transaction struct {
	paymentInstitutionID string `json:"payment_institution_id"`
	paymentUserID string `json:"payment_user_id"`
	collectionInstitutionID string `json:"collection_institution_id"`
	collectionUserID string `json:"collection_user_id"`
	dateTime string `json:"date_time"`
	sum string `json:"sum"`
}

func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) sc.Response  {
	return shim.Success(nil)

}

func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {
	function, args := APIstub.GetFunctionAndParameters()

	if function == "createTransaction" {
		return s.createTransaction(APIstub,args)
	}else if function == "queryTransaction" {
		return s.queryTransaction(APIstub,args)
	}
	return shim.Error("Invalid Smart Contract function name.")
}

func (s *SmartContract) initLedger(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (s *SmartContract) createTransaction (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 7{
		return shim.Error("Incorrect number of arguments. Excepting 7")
	}
	var trans = transaction{paymentInstitutionID:args[1],paymentUserID:args[2],
							collectionInstitutionID:args[3],collectionUserID:args[4],
							dateTime:args[5],sum:args[6]}
	transAsBytes,_ := json.Marshal(trans)
	APIstub.PutState(args[0],transAsBytes)
	return shim.Success(nil)
}

func (s *SmartContract) queryTransaction (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 1{
		return shim.Error("Incorrect number of arguments. Excepting 1")
	}
	transAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(transAsBytes)
}
func main() {


	err := shim.Start(new(SmartContract))
	if err != nil{
		fmt.Printf("Error creating new Smart Constract: %s",err)
	}
}
