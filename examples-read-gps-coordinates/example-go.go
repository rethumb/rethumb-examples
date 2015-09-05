package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"strings"
	"math"
)

func main() {
	resp, err := http.Get("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")
	if err != nil {
		log.Fatal(err)
	}
	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatal(err)
	}

	var dat rethumbResponse
	if err := json.Unmarshal(body, &dat); err != nil {
		log.Fatal(err)
	}
	fmt.Println(dat.GPS)
}

type rethumbResponse struct {
	GPS GPS
}

type GPS struct {
	Lat       degMinSec `json:"GPSLatitude"`
	LatLabel  string    `json:"GPSLatitudeRef"`
	Long      degMinSec `json:"GPSLongitude"`
	LongLabel string    `json:"GPSLongitudeRef"`
}

func (gps GPS) String() string {
	return fmt.Sprintf("%s%s %s%s", gps.Lat, gps.LatLabel, gps.Long, gps.LongLabel)
}

type degMinSec [3]precFloat

func (d degMinSec) String() string {
	return fmt.Sprintf("%v° %v' %v''", d[0], d[1], d[2])
}

type precFloat struct {
	n    float64
	prec int
}

func (f *precFloat) UnmarshalJSON(data []byte) error {
	var s string
	if err := json.Unmarshal(data, &s); err != nil {
		return err
	}
	var prec string
	if n, err := fmt.Sscanf(s, "%g/%s", &f.n, &prec); n != 2 || err != nil {
		return fmt.Errorf("badly formed value %q", s)
	}
	if strings.TrimRight(prec, "0") != "1" {
		return fmt.Errorf("precision is not power of 10")
	}
	f.prec = len(prec) - 1
	return nil
}

func (f precFloat) String() string {
	return fmt.Sprintf("%.*f", f.prec, f.n / math.Pow(10, float64(f.prec)))
}
