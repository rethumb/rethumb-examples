using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        string paramOperation1 = "height";
        int paramValue1 = 100; // New height in pixels.

        string paramOperation2 = "width";
        int paramValue2 = 100; // New width in pixels.

        string imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
        string imageFilename = "resized-image.jpg";

        using (WebClient client = new WebClient())
        {
            client.DownloadFile(string.Format("http://api.rethumb.com/v1/{0}/{1}/{2}/{3}/{4}", paramOperation1, paramValue1, paramOperation2, paramValue2, imageURL), imageFilename);
        }
    }
}
