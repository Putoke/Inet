package main
import (
    "webtest/controllers"
    "log"
    "net/http"
    "webtest/models"
    "github.com/goji/httpauth"
)

func main() {
    router := controllers.NewRouter()
    models.InitDB()

    http.Handle("/", httpauth.SimpleBasicAuth("test", "korv")(router))

    log.Fatal(http.ListenAndServe(":8000", nil))
    models.CloseDB()
}