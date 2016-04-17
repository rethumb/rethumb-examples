import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object KotlinRethumbWidthHeightExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val paramOperation1 = "height"
        val paramValue1 = 100 // New height in pixels.

        val paramOperation2 = "width"
        val paramValue2 = 100 // New width in pixels.

        val imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg"
        val imageFilename = "resized-image.jpg"

        val url = URL(String.format("http://api.rethumb.com/v1/%s/%s/%s/%s/%s", paramOperation1, paramValue1, paramOperation2, paramValue2, imageURL))
        val fos = FileOutputStream(imageFilename)
        fos.channel.transferFrom(Channels.newChannel(url.openStream()), 0, java.lang.Long.MAX_VALUE)
    }
}