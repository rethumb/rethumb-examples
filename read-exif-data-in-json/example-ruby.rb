require 'net/http'
require 'json'

Net::HTTP.start("api.rethumb.com") do |http|
    response = http.get("/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

    json = JSON.parse(response.body)

    print json
end