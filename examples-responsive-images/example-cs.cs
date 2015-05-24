using System;

class CsRethumbExample
{
    static void Main()
    {
        string image_url = "http://images.rethumb.com/image_coimbra_999x999.jpg";

        int small_width = 400; // Image width for small resolutions (less than 400px). 
        int large_width = 800; // Image width for medium resolutions (less than 800px).
        int image_width = 999; // Image original width (used for resolutions larger than 800px).

        Console.WriteLine(
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/picturefill/2.3.1/picturefill.min.js\"></script>\n"+
            "\n"+
            "<img src=\"http://api.rethumb.com/v1/width/"+small_width+"/"+image_url+"\"\n"+
            "\n"+
            "     srcset=\"http://api.rethumb.com/v1/width/"+small_width+"/"+image_url+" "+small_width+"w,\n"+
            "             http://api.rethumb.com/v1/width/"+large_width+"/"+image_url+" "+large_width+"w,\n"+
            "             "+image_url+" "+image_width+"w\"\n"+
            "\n"+
            "     sizes=\"100vw\" />"
        );

    }
}