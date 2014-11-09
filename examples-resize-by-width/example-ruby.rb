require 'net/http'

width = 100; # New width in pixels.
image = "http://factor45.org/images/beach.jpg";
newImage = "beach.thumb.jpg";

Net::HTTP.start("api.rethumb.com") do |http|
    resp = http.get("/v1/width/#{width}/#{image}")
    open(newImage, "wb") do |file|
        file.write(resp.body)
    end
end