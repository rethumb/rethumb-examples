<?php
    $param1_operation = "height";
    $param1_value = 100; // New height in pixels.

    $param2_operation = "width";
    $param2_value = 100; // New width in pixels.
    
    $image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg";
    $image_filename = "resized-image.jpg";

    file_put_contents($image_filename, file_get_contents("http://api.rethumb.com/v1/$param1_operation/$param1_value/$param2_operation/$param2_value/$image_url"));
?>