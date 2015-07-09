<?php

    $response  = file_get_contents("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

    $json = json_decode($response);

    print_r(parseGPSCoordinates($json));

    function parseGPSCoordinates($data)
    {
        if ($data->{'GPS'} === null)
            return 'GPS Coordinates not found';

        $values = [];

        $values['LAT'] = $data->{'GPS'}->{'GPSLatitudeRef'};
        $values['LONG'] = $data->{'GPS'}->{'GPSLongitudeRef'};
        $values['LAT_DEG'] = applyDivision($data->{'GPS'}->{'GPSLatitude'}[0]);
        $values['LAT_MIN'] = applyDivision($data->{'GPS'}->{'GPSLatitude'}[1]);
        $values['LAT_SEC'] = applyDivision($data->{'GPS'}->{'GPSLatitude'}[2]);
        $values['LONG_DEG'] = applyDivision($data->{'GPS'}->{'GPSLongitude'}[0]);
        $values['LONG_MIN'] = applyDivision($data->{'GPS'}->{'GPSLongitude'}[1]);
        $values['LONG_SEC'] = applyDivision($data->{'GPS'}->{'GPSLongitude'}[2]);

        return format("{LAT} {LAT_DEG}° {LAT_MIN}' {LAT_SEC}'' {LONG} {LONG_DEG}° {LONG_MIN}' {LONG_SEC}''", $values);
    }

    function applyDivision($value)
    {
        $tokens = explode('/', $value);
        return intval($tokens[0]) / intval($tokens[1]);
    }

    function format($str, $values)
    {
        foreach ($values as $key => $value)
            $str = str_replace('{' . $key . '}', $value, $str);

        return $str;
    }

?>