import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object KotlinRethumbFormatExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        // First apply a square operation, then convert to PNG format.
        val paramOperation1 = "square"
        val paramValue1 = 100

        val paramOperation2 = "format"
        val paramValue2 = "png" // Other formats available: jpg, gif, tiff and webp..

        val imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg"
        val imageFilename = "resized-image.jpg"

        val url = URL(String.format("http://api.rethumb.com/v1/%s/%s/%s/%s/%s", paramOperation1, paramValue1, paramOperation2, paramValue2, imageURL))
        val fos = FileOutputStream(imageFilename)
        fos.channel.transferFrom(Channels.newChannel(url.openStream()), 0, java.lang.Long.MAX_VALUE)
    }
}