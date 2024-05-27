package methods

import (
	"bytes"
	"encoding/json"
	"fmt"
	"log"
	"mts/graph/model"
	"mts/users"
	"net/http"

	"github.com/gin-gonic/gin"
)

func Ping(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": "pong",
	})
}

type RequestData struct {
	Text string `json:"text"`
}

func Speech(ctx *gin.Context) {
	var dto map[string]any

	if err := json.NewDecoder(ctx.Request.Body).Decode(&dto); err != nil {
		ctx.AbortWithError(http.StatusBadRequest, err)
		return
	}

	log.Println(dto["message"])

	text, _ := dto["message"].(string)

	requestData := RequestData{
		Text: text,
	}

	jsonData, err := json.Marshal(requestData)
	if err != nil {
		fmt.Println("Error marshalling JSON:", err)
		return
	}

	req, err := http.NewRequest("POST", "http://localhost:8000/submit-text", bytes.NewBuffer(jsonData))
	if err != nil {
		fmt.Println("Error creating request:", err)
		return
	}

	req.Header.Set("Content-Type", "application/json")

	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		fmt.Println("Error sending request:", err)
		return
	}
	defer resp.Body.Close()

	fmt.Println("Response status:", resp.Status)

	if err = json.NewDecoder(resp.Body).Decode(&dto); err != nil {
		log.Println("Error decoding response:", err)

		ctx.AbortWithError(http.StatusInternalServerError, err)

		return
	}

	ctx.JSON(http.StatusOK, gin.H{
		"message": dto["result"],
	})
}

func Contacts(ctx *gin.Context) {
	c := make([]*model.User, 0, len(users.Users))

	for _, v := range users.Users {
		c = append(c, v)
	}

	ctx.JSON(http.StatusOK, gin.H{
		"contacts": c,
	})
}
