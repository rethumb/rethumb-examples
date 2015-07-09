using System.IO;
using System;
using System.Net;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Globalization;

class CsJavaRethumbJsonGPSExample
{
    static void Main()
    {
        using (WebClient client = new WebClient())
        {
            string response = client.DownloadString("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

            JToken data = JObject.Parse(response);

            Console.WriteLine(parseGPSCoordinates(data));
        }
    }

    static string parseGPSCoordinates(JToken data)
    {
        if (data.SelectToken("GPS") == null)
            return "GPS Coordinates not found";

        Dictionary<string, string> values = new Dictionary<string, string>();

        values["LAT"] = data.SelectToken("GPS").SelectToken("GPSLatitudeRef").ToString();
        values["LONG"] = data.SelectToken("GPS").SelectToken("GPSLongitudeRef").ToString();
        values["LAT_DEG"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLatitude").ToObject<List<string>>()[0]);
        values["LAT_MIN"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLatitude").ToObject<List<string>>()[1]);
        values["LAT_SEC"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLatitude").ToObject<List<string>>()[2]);
        values["LONG_DEG"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLongitude").ToObject<List<string>>()[0]);
        values["LONG_MIN"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLongitude").ToObject<List<string>>()[1]);
        values["LONG_SEC"] = applyDivision(data.SelectToken("GPS").SelectToken("GPSLongitude").ToObject<List<string>>()[2]);

        return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values);
    }

    static string applyDivision(string value)
    {
        var tokens = value.Split('/');
        return (int.Parse(tokens[0]) / float.Parse(tokens[1])).ToString(CultureInfo.InvariantCulture);
    }

    static string format(string str, Dictionary<string, string> values)
    {
        foreach (string key in values.Keys)
            str = str.Replace('{' + key + '}', values[key]);

        return str;
    }
}