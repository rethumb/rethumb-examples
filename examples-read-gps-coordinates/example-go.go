package main

import (
	"fmt";
	"net/http";
	"io/ioutil";
	"encoding/json";
	"strings";
	"strconv"
)

func main() {
	resp, err := http.Get("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")
	check(err)
	defer resp.Body.Close()
	
	body, err := ioutil.ReadAll(resp.Body)
	check(err)

	var dat map[string]interface{}
	if err := json.Unmarshal(body, &dat); err != nil {
		panic(err)
	}
	fmt.Println(parseGPSCoordinates(dat))
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func parseGPSCoordinates(data map[string]interface{}) string {
	values := make(map[string]string)
	gps := data["GPS"].(map[string]interface{})

	values["LAT"] = gps["GPSLatitudeRef"].(string)
	values["LONG"] = gps["GPSLongitudeRef"].(string)
	values["LAT_DEG"] = applyDivision(gps["GPSLatitude"].([]interface{})[0].(string));
	values["LAT_MIN"] = applyDivision(gps["GPSLatitude"].([]interface{})[1].(string));
	values["LAT_SEC"] = applyDivision(gps["GPSLatitude"].([]interface{})[2].(string));
	values["LONG_DEG"] = applyDivision(gps["GPSLongitude"].([]interface{})[0].(string));
	values["LONG_MIN"] = applyDivision(gps["GPSLongitude"].([]interface{})[1].(string));
	values["LONG_SEC"] = applyDivision(gps["GPSLongitude"].([]interface{})[2].(string));

	return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values);
}

func applyDivision(value string) string {
	tokens := strings.Split(value, "/");
	n, _ := strconv.ParseFloat(tokens[0], 64)
	d, _ := strconv.ParseFloat(tokens[1], 64)
	r := n / d;
	
	if r == float64(int64(r)) {
		return strconv.FormatInt(int64(r), 10)
	} else {
		return strconv.FormatFloat(r, 'f', 3, 64)
	}
}

func format(str string, values map[string]string) string {
	for key, value := range values {
		str = strings.Replace(str, "{" + key + "}", value, -1);
	}

	return str;
}
