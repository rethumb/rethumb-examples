using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        int width = 100; // New width in pixels.
        string image = "http://factor45.org/images/beach.jpg";
        string newImage = @"beach.thumb.jpg";

        using (WebClient client = new WebClient())
        {
            client.DownloadFile(string.Format("http://api.rethumb.com/v1/width/{0}/{1}", width, image), newImage);
        }
    }
}