var http = require("http");
var fs = require("fs");

var paramOperation = "cover";
var paramValue = "150x200"; // New WIDTHxHEIGHT in pixels.

var imageURL = "http://images.rethumb.com/image_coimbra_600x300.jpg";
var imageFilename = "resized-image.jpg";

http.get("http://api.rethumb.com/v1/" + paramOperation + "/" + paramValue + "/" + imageURL,
    function(response) {
        response.pipe(fs.createWriteStream(imageFilename));
    }
);