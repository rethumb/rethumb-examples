public class JavaRethumbExample
{
    public static void main(String [] args) throws Exception
    {
        String imageUrl = "http://images.rethumb.com/image_coimbra_999x999.jpg";

        int smallWidth = 400; // Image width for small resolutions (less than 400px). 
        int largeWidth = 800; // Image width for medium resolutions (less than 800px).
        int imageWidth = 999; // Image original width (used for resolutions larger than 800px).

        System.out.println(
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/picturefill/2.3.1/picturefill.min.js\"></script>\n"+
            "\n"+
            "<img src=\"http://api.rethumb.com/v1/width/"+smallWidth+"/"+imageUrl+"\"\n"+
            "\n"+
            "     srcset=\"http://api.rethumb.com/v1/width/"+smallWidth+"/"+imageUrl+" "+smallWidth+"w,\n"+
            "             http://api.rethumb.com/v1/width/"+largeWidth+"/"+imageUrl+" "+largeWidth+"w,\n"+
            "             "+imageUrl+" "+imageWidth+"w\"\n"+
            "\n"+
            "     sizes=\"100vw\" />"
        );
    }
}