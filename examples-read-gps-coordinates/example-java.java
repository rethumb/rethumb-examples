import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaRethumbJsonGPSExample
{
    public static void main(String[] args) throws Exception
    {
        URL url = new URL("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        Gson gson = new Gson();
        LinkedTreeMap result = gson.fromJson(reader , LinkedTreeMap.class);

        System.out.println(parseGPSCoordinates(result));
    }

    private static String parseGPSCoordinates(LinkedTreeMap data)
    {
        if (data.get("GPS") == null)
            return "GPS Coordinates not found";

        Map<String, String> values = new HashMap<>();

        values.put("LAT", ((LinkedTreeMap) data.get("GPS")).get("GPSLatitudeRef").toString());
        values.put("LONG", ((LinkedTreeMap)data.get("GPS")).get("GPSLongitudeRef").toString());
        values.put("LAT_DEG", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLatitude")).get(0)));
        values.put("LAT_MIN", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLatitude")).get(1)));
        values.put("LAT_SEC", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLatitude")).get(2)));
        values.put("LONG_DEG", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLongitude")).get(0)));
        values.put("LONG_MIN", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLongitude")).get(1)));
        values.put("LONG_SEC", "" + applyDivision(((List<String>)((LinkedTreeMap) data.get("GPS")).get("GPSLongitude")).get(2)));

        return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values);
    }

    private static String applyDivision(String value)
    {
        String[] tokens = value.split("/");
        return trim(Float.parseFloat(tokens[0]) / Float.parseFloat(tokens[1]));
    }

    private static String trim(float f)
    {
        System.out.println(f);
        if (f == Math.ceil(f))
            return "" + ((int) f);
        return "" + f;
    }

    private static String format(String str, Map<String, String> values)
    {
        for (String key : values.keySet())
            str = str.replace("{" + key + "}", values.get(key));
        return str;
    }
}