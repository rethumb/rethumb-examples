var http = require('http');
var fs = require('fs');

var file = fs.createWriteStream("beach.thumb.jpg");
var request = http.get("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg", function(response) {
  response.pipe(file);
});