package main

import (
	"fmt";
	"net/http";
	"io/ioutil"
)

func main() {
	paramOperation := "height";
	paramValue := "100"; // New height in pixels.

	imageURL := "http://images.rethumb.com/image_coimbra_600x300.jpg";
	imageFilename := "resized-image.jpg";

	resp, err := http.Get(fmt.Sprintf("http://api.rethumb.com/v1/%s/%s/%s", paramOperation, paramValue, imageURL))
	check(err)
	defer resp.Body.Close()
	
	body, err := ioutil.ReadAll(resp.Body)
	check(err)

	err = ioutil.WriteFile(imageFilename, body, 0644)
	check(err)
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}