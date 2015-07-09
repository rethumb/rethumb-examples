# -*- encoding: utf-8 -*-

import urllib2
import json

def main():
    response = urllib2.urlopen("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

    json_data = json.loads(response.read())

    print parseGPSCoordinates(json_data)

def parseGPSCoordinates(data):
    if (data['GPS'] is None):
        return 'GPS Coordinates not found';

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


def applyDivision(value):
    tokens = value.split('/');
    return trim(int(tokens[0]) / float(tokens[1]));

def trim(f):
    if f.is_integer():
        return int(f)
    return f


def format(s, values):
    for key in values:
        s = s.replace('{' + key + '}', str(values[key]));

    return s;

main()