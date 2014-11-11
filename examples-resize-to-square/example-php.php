<?php
    $param_operation = "square";
    $param_value = 100; // Square size in pixels.
    
    $image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg";
    $image_filename = "resized-image.jpg";

    file_put_contents($image_filename, file_get_contents("http://api.rethumb.com/v1/$param_operation/$param_value/$image_url"));
?>