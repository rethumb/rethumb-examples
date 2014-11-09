using System.IO;
using System;
using System.Net;

class CsRethumbExample
{
    static void Main()
    {
        using (WebClient client = new WebClient())
        {
            client.DownloadFile("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg",  @"beach.thumb.jpg");
        }
    }
}