import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*

object KotlinRethumbGPSExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {
        val url = URL("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")
        val reader = BufferedReader(InputStreamReader(url.openStream()))
        val gson = Gson()
        val result = gson.fromJson(reader, LinkedTreeMap::class.java)
        println(parseGPSCoordinates(result))
    }

    private fun parseGPSCoordinates(data: LinkedTreeMap<*, *>): String {
        if (data.get("GPS") == null)
            return "GPS Coordinates not found"
        val values = HashMap<String, String>()
        values.put("LAT", (data.get("GPS") as LinkedTreeMap<*, *>).get("GPSLatitudeRef").toString())
        values.put("LONG", (data.get("GPS") as LinkedTreeMap<*, *>).get("GPSLongitudeRef").toString())
        values.put("LAT_DEG", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLatitude"] as List<*>)[0] as String))
        values.put("LAT_MIN", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLatitude"] as List<*>)[1] as String))
        values.put("LAT_SEC", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLatitude"] as List<*>)[2] as String))
        values.put("LONG_DEG", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLongitude"] as List<*>)[0] as String))
        values.put("LONG_MIN", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLongitude"] as List<*>)[1] as String))
        values.put("LONG_SEC", "" + applyDivision(((data["GPS"] as LinkedTreeMap<*, *>)["GPSLongitude"] as List<*>)[2] as String))
        return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values)
    }

    private fun applyDivision(value: String): String {
        val tokens = value.split(("/").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        return trim(java.lang.Float.parseFloat(tokens[0]) / java.lang.Float.parseFloat(tokens[1]))
    }

    private fun trim(f: Float): String {
        if (f.toDouble() == Math.ceil(f.toDouble()))
            return "" + (f.toInt())
        return "" + f
    }

    private fun format(str: String, values: Map<String, String>): String {
        var out: String = str;
        for (key in values.keys)
            out = out.replace("{$key}", values[key].toString())
        return out
    }
}