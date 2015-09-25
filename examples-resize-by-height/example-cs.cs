using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        string paramOperation = "height";
        int paramValue = 100; // New height in pixels.
        
        string imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
        string imageFilename = "resized-image.jpg";

        using (WebClient client = new WebClient())
        {
            client.DownloadFile(string.Format("http://api.rethumb.com/v1/{0}/{1}/{2}", paramOperation, paramValue, imageURL), imageFilename);
        }
    }
}