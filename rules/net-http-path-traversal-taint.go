package main

import (
	"net/http"
	"os"
	"path"
)

func asdf(req *http.Request) {
	username, _, _ := req.BasicAuth()

	// ruleid: net-http-path-traversal-taint
	os.Open(username)

	unsanpath := path.Join("../", username)
	// ruleid: net-http-path-traversal-taint
	os.Open(unsanpath)

	sanpath := path.Clean(username)
	// ok: net-http-path-traversal-taint
	os.Open(sanpath)

}
