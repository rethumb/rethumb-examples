import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class JavaRethumbExample
{
    public static void main(String [] args) throws Exception {
        URL website = new URL("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg");

        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("beach.thumb.jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

}