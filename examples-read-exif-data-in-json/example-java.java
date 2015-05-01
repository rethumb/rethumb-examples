import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JavaRethumbJsonExample
{
    public static void main(String[] args) throws Exception
    {
        URL url = new URL("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String s = null;
        while ((s = reader.readLine()) != null)
            System.out.println(s);

        // Use your favorite json library to parse the response!
    }
}