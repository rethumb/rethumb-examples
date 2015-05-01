var http = require("http");

http.get("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg",
    function(req) {
        var body = '';

        req.on('data', function(chunk) {
            body += chunk;
        });

        req.on('end', function() {

            var json = JSON.parse(body);

            console.log(json);
        });
    }
);