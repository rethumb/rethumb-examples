require 'net/http'

# First apply a square operation, then convert to PNG format.

param_operation1 = "square"
param_value1 = 100

param_operation2 = "format"
param_value2 = "png" # Other formats available: jpg, gif, tiff and webp.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

Net::HTTP.start("api.rethumb.com") do |http|
    resp = http.get("/v1/#{param_operation1}/#{param_value1}/#{param_operation2}/#{param_value2}/#{image_url}")
    open(image_filename, "wb") do |file|
        file.write(resp.body)
    end
end
