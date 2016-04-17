import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object KotlinRethumbWidthExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val paramOperation = "width"
        val paramValue = 100 // New width in pixels.

        val imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg"
        val imageFilename = "resized-image.jpg"

        val url = URL(String.format("http://api.rethumb.com/v1/%s/%s/%s", paramOperation, paramValue, imageURL))
        val fos = FileOutputStream(imageFilename)
        fos.channel.transferFrom(Channels.newChannel(url.openStream()), 0, java.lang.Long.MAX_VALUE)
    }
}