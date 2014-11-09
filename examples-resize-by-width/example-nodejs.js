var http = require('http');
var fs = require('fs');

var width = 100; // New width in pixels.
var image = "http://factor45.org/images/beach.jpg";
var newImage = "beach.thumb.jpg";

var file = fs.createWriteStream(newImage);
var request = http.get("http://api.rethumb.com/v1/width/" + width + "/" + image, function(response) {
    response.pipe(file);
});