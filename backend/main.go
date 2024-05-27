package main

import (
	"log"
	"mts/users"
	"net/http"

	"mts/graph"
	"mts/methods"

	"github.com/99designs/gqlgen/graphql/handler"
	"github.com/99designs/gqlgen/graphql/playground"
	"github.com/gin-gonic/gin"
)

// Defining the Graphql handler
func graphqlHandler() gin.HandlerFunc {
	// NewExecutableSchema and Config are in the generated.go file
	// Resolver is in the resolver.go file
	h := handler.NewDefaultServer(graph.NewExecutableSchema(graph.Config{Resolvers: &graph.Resolver{
		Users: users.Users,
	}}))

	return func(c *gin.Context) {
		h.ServeHTTP(c.Writer, c.Request)
	}
}

// Defining the Playground handler
func playgroundHandler() gin.HandlerFunc {
	h := playground.Handler("GraphQL", "/graphql")

	return func(c *gin.Context) {
		h.ServeHTTP(c.Writer, c.Request)
	}
}

func main() {
	r := gin.Default()
	r.GET("/ping", methods.Ping)
	r.POST("/speech", methods.Speech)
	r.GET("/contacts", methods.Contacts)

	r.POST("/graphql", graphqlHandler())
	r.GET("/", playgroundHandler())

	log.Println("server running on port 8001")
	log.Printf("connect to http://localhost:8001/ for GraphQL playground")

	log.Fatal(http.ListenAndServe(":8001", r))
}
