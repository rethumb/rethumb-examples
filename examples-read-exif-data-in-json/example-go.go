package main

import (
	"fmt";
	"net/http";
	"io/ioutil";
	"encoding/json"
)

func main() {
	resp, err := http.Get("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")
	check(err)
	defer resp.Body.Close()
	
	body, err := ioutil.ReadAll(resp.Body)
	check(err)

	byt := []byte(body)
	var dat map[string]interface{}
	if err := json.Unmarshal(byt, &dat); err != nil {
		panic(err)
	}
	fmt.Println(dat)
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}