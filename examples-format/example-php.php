<?php

    // First apply a square operation, then convert to PNG format.

    $param1_operation = "square";
    $param1_value = 100;

    $param2_operation = "format";
    $param2_value = "png"; // Other formats available: jpg, gif, tiff and webp.

    $image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg";
    $image_filename = "resized-image.jpg";

    file_put_contents($image_filename, file_get_contents("http://api.rethumb.com/v1/$param1_operation/$param1_value/$param2_operation/$param2_value/$image_url"));
?>
