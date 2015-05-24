<?php

    $image_url = "http://images.rethumb.com/image_coimbra_999x999.jpg";
    
    $small_width = 400; // Image width for small resolutions (less than 400px). 
    $large_width = 800; // Image width for medium resolutions (less than 800px).
    $image_width = 999; // Image original width (used for resolutions larger than 800px).
    
    echo <<<END

<script src="https://cdnjs.cloudflare.com/ajax/libs/picturefill/2.3.1/picturefill.min.js"></script>

<img src="http://api.rethumb.com/v1/width/{$small_width}/{$image_url}"

     srcset="http://api.rethumb.com/v1/width/{$small_width}/{$image_url} {$small_width}w,
             http://api.rethumb.com/v1/width/{$large_width}/{$image_url} {$large_width}w,
             {$image_url} {$image_width}w"

     sizes="100vw" />

END;
?>