var http = require("http");
var fs = require("fs");

// First apply a square operation, then convert to PNG format.

var paramOperation1 = "square";
var paramValue1 = 100;

var paramOperation2 = "format";
var paramValue2 = "png"; // Other formats available: jpg, gif, tiff and webp.

var imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
var imageFilename = "resized-image.jpg";

http.get("http://api.rethumb.com/v1/" + paramOperation1 + "/" + paramValue1 + "/" + paramOperation2 + "/" + paramValue2 + "/" + imageURL,
    function(response) {
        response.pipe(fs.createWriteStream(imageFilename));
    }
);
