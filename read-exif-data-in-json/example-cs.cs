using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        using (WebClient client = new WebClient())
        {
            string response = client.DownloadString("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

            Console.Write(response);

            // Use your favorite json library to parse the response!
        }
    }
}