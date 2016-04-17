import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object KotlinRethumbSquareExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {
        val paramOperation = "square"
        val paramValue = 100 // Square size in pixels.

        val imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg"
        val imageFilename = "resized-image.jpg"

        val url = URL(String.format("http://api.rethumb.com/v1/%s/%s/%s", paramOperation, paramValue, imageURL))
        val fos = FileOutputStream(imageFilename)
        fos.channel.transferFrom(Channels.newChannel(url.openStream()), 0, java.lang.Long.MAX_VALUE)
    }
}