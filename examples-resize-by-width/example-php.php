<?php
    $width = 100; // New width in pixels.
    $image = "http://factor45.org/images/beach.jpg";
    $newImage = "beach.thumb.jpg";

    $thumbnail = file_get_contents("http://api.rethumb.com/v1/width/$width/$image");
    file_put_contents($newImage, $thumbnail);
?>