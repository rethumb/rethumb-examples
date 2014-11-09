import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class JavaRethumbExample
{
    public static void main(String [] args) throws Exception {
        int width = 100; // New width in pixels.
        String image = "http://factor45.org/images/beach.jpg";
        String newImage = "beach.thumb.jpg";

        URL url = new URL(String.format("http://api.rethumb.com/v1/width/%s/%s", width, image));
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(newImage);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}