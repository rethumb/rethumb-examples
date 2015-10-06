require 'net/http'

param_operation1 = "height"
param_value1 = 100 # New height in pixels.

param_operation2 = "width"
param_value2 = 100 # New width in pixels.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

Net::HTTP.start("api.rethumb.com") do |http|
    resp = http.get("/v1/#{param_operation1}/#{param_value1}/#{param_operation2}/#{param_value2}/#{image_url}")
    open(image_filename, "wb") do |file|
        file.write(resp.body)
    end
end
