package main

import (
	"net/http";
	"io/ioutil"
)

func main() {
	resp, err := http.Get("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg")
	check(err)
	defer resp.Body.Close()
	
	body, err := ioutil.ReadAll(resp.Body)
	check(err)

	err = ioutil.WriteFile("beach.thumb.jpg", body, 0644)
	check(err)
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}