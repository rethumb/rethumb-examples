import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object KotlinRethumbExifExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val url = URL("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

        val reader = BufferedReader(InputStreamReader(url.openStream()))
        var s = reader.readLine()
        while (s != null) {
            println(s)
            s = reader.readLine()
        }

        // Use your favorite json library to parse the response!
    }
}