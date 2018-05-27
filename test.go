package main

import (
	"encoding/json"
	"fmt"
)

type test struct {
	InstitutionID string `json:"institution_id"`
	UserID string `json:"user_id"`
	DateTime string `json:"date_time"`
	Type string `json:"type"`
	Sum string `json:"sum"`// negative for withdraw, positive for top
}

func main() {
	var args = []string{"12","23","34","45","56"}
	var h = test{InstitutionID:args[0],UserID:args[1],DateTime:args[2],Type:args[3],Sum:args[4]}
	hAsBytes,_ := json.Marshal(h)
	fmt.Println("test")
	fmt.Print(hAsBytes)
}