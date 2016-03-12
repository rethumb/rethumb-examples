package main

import (
	"fmt";
	"net/http";
	"io/ioutil"
)

func main() {
	// First apply a square operation, then convert to PNG format.

	paramOperation1 := "square";
	paramValue1 := "100";

	paramOperation2 := "format";
	paramValue2 := "png"; // Other formats available: jpg, gif, tiff and webp..

	imageURL := "http://images.rethumb.com/image_coimbra_600x300.jpg";
	imageFilename := "resized-image.jpg";

	resp, err := http.Get(fmt.Sprintf("http://api.rethumb.com/v1/%s/%s/%s/%s/%s", paramOperation1, paramValue1, paramOperation2, paramValue2, imageURL))
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
