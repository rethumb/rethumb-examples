<?php
    $thumbnail = file_get_contents("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg");
    file_put_contents("beach.thumb.jpg", $thumbnail);
?>