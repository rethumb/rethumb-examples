require 'net/http'

param_operation = "width"
param_value = 100 # New width in pixels.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

Net::HTTP.start("api.rethumb.com") do |http|
    resp = http.get("/v1/#{param_operation}/#{param_value}/#{image_url}")
    open(image_filename, "wb") do |file|
        file.write(resp.body)
    end
end