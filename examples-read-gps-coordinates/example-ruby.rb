require 'net/http'
require 'json'

def main()
    Net::HTTP.start("api.rethumb.com") do |http|
        response = http.get("/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

        json = JSON.parse(response.body)

        print parseGPSCoordinates(json)
    end
end

def parseGPSCoordinates(data)
    if (data['GPS'] == nil)
        return 'GPS Coordinates not found';
    end

    values = {};

    values['LAT'] = data['GPS']['GPSLatitudeRef'];    
    values['LONG'] = data['GPS']['GPSLongitudeRef'];
    values['LAT_DEG'] = applyDivision(data['GPS']['GPSLatitude'][0]);
    values['LAT_MIN'] = applyDivision(data['GPS']['GPSLatitude'][1]);
    values['LAT_SEC'] = applyDivision(data['GPS']['GPSLatitude'][2]);
    values['LONG_DEG'] = applyDivision(data['GPS']['GPSLongitude'][0]);
    values['LONG_MIN'] = applyDivision(data['GPS']['GPSLongitude'][1]);
    values['LONG_SEC'] = applyDivision(data['GPS']['GPSLongitude'][2]);

    return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", values);
end


def applyDivision(value)
    tokens = value.split('/');
    return trim(tokens[0].to_f / tokens[1].to_f);
end

def trim(f)
    if f % 1 == 0
        return f.to_i
    end

    return f
end


def format(s, values)
    values.each do |key, value|
        s.sub! '{' + key.to_s + '}', value.to_s
    end

    return s;
end

main()