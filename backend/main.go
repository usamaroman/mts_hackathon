package main

import (
	"log"
	"net/http"

	"mts/methods"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	r.GET("/ping", methods.Ping)
	r.POST("/speech", methods.Speech)
	r.GET("/contacts", methods.Contacts)

	log.Println("server running on port 8080")
	http.ListenAndServe(":8080", r)
}
