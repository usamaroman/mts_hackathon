package methods

import (
	"encoding/json"
	"log"
	"net/http"
	
	"mts/users"

	"github.com/gin-gonic/gin"
)

func Ping(ctx *gin.Context) {
	ctx.JSON(http.StatusOK, gin.H{
		"message": "pong",
	})
}

func Speech(ctx *gin.Context) {
	var req map[string]any

	if err := json.NewDecoder(ctx.Request.Body).Decode(&req); err != nil {
		ctx.AbortWithError(http.StatusBadRequest, err)
		return
	}

	log.Println(req["message"])

	ctx.JSON(http.StatusOK, gin.H{
		"message": "pong",
	})
}

func Contacts(ctx *gin.Context) {
	c := make([]*users.User, 0, len(users.Users))

	for _, v := range users.Users {
		c = append(c, v)
	}

	ctx.JSON(http.StatusOK, gin.H{
		"contacts": c,
	})
}