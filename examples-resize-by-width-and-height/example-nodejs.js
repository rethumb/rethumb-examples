var http = require("http");
var fs = require("fs");

var paramOperation1 = "height";
var paramValue1 = 100; // New height in pixels.

var paramOperation2 = "width";
var paramValue2 = 100; // New width in pixels.

var imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
var imageFilename = "resized-image.jpg";

http.get("http://api.rethumb.com/v1/" + paramOperation1 + "/" + paramValue1 + "/" + paramOperation2 + "/" + paramValue2 + "/" + imageURL,
    function(response) {
        response.pipe(fs.createWriteStream(imageFilename));
    }
);
