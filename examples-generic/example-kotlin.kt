import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

object KotlinRethumbGenericExample {

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val website = URL("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg")

        val rbc = Channels.newChannel(website.openStream())
        val fos = FileOutputStream("beach.thumb.jpg")
        fos.channel.transferFrom(rbc, 0, java.lang.Long.MAX_VALUE)
    }
}