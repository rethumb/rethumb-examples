<?php

    $response  = file_get_contents("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg");

    $json = json_decode($response);

    print_r($json);

?>