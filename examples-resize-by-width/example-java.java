import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class JavaRethumbExample
{
    public static void main(String [] args) throws Exception
    {
        String paramOperation = "width";
        int paramValue = 100; // New width in pixels.
        String imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
        String imageFilename = "resized-image.jpg";

        URL url = new URL(String.format("http://api.rethumb.com/v1/%s/%s/%s", paramOperation, paramValue, imageURL));
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(imageFilename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}