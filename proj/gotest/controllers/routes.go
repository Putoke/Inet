package controllers

import (
    "net/http"
    "webtest/models"
)

type Route struct {
    Name string
    Method string
    Pattern string
    HandlerFunc http.HandlerFunc
}

type Routes []Route

var routes = Routes {
    Route{
        "Index",
        "GET",
        "/json/trades",
        models.Index,
    },
}