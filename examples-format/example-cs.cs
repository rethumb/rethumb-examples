using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        // First apply a square operation, then convert to PNG format.

        string paramOperation1 = "square";
        int paramValue1 = 100;

        string paramOperation2 = "format";
        string paramValue2 = "png"; // Other formats available: jpg, gif, tiff and webp.

        string imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
        string imageFilename = "resized-image.jpg";

        using (WebClient client = new WebClient())
        {
            client.DownloadFile(string.Format("http://api.rethumb.com/v1/{0}/{1}/{2}/{3}/{4}", paramOperation1, paramValue1, paramOperation2, paramValue2, imageURL), imageFilename);
        }
    }
}
