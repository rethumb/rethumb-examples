var http = require("http");

http.get("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg",
    function(req) {
        var body = '';

        req.on('data', function(chunk) {
            body += chunk;
        });

        req.on('end', function() {
            try
            {
                var json = JSON.parse(body);
                
                var gpsCoordinates = parseGPSCoordinates(json);
                
                console.log(gpsCoordinates);
            }
            catch (e)
            {
                console.log('GPS Coordinates not found');
            }
        });
    }
);

function parseGPSCoordinates(data)
{
    if (data['GPS'] === undefined)
        return 'GPS Coordinates not found';

    var values = {};

    values['LAT'] = data['GPS']['GPSLatitudeRef'];    
    values['LONG'] = data['GPS']['GPSLongitudeRef'];
    values['LAT_DEG'] = applyDivision(data['GPS']['GPSLatitude'][0]);
    values['LAT_MIN'] = applyDivision(data['GPS']['GPSLatitude'][1]);
    values['LAT_SEC'] = applyDivision(data['GPS']['GPSLatitude'][2]);
    values['LONG_DEG'] = applyDivision(data['GPS']['GPSLongitude'][0]);
    values['LONG_MIN'] = applyDivision(data['GPS']['GPSLongitude'][1]);
    values['LONG_SEC'] = applyDivision(data['GPS']['GPSLongitude'][2]);

    return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values);
}

function applyDivision(value)
{
    var tokens = value.split('/');
    return parseInt(tokens[0], 10) / parseInt(tokens[1], 10);
}

function format(str, values)
{
    Object.keys(values).forEach(function(key) {
        str = str.replace('{' + key + '}', values[key]);
    });

    return str;
}