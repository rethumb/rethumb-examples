require 'net/http'

Net::HTTP.start("api.rethumb.com") do |http|
    resp = http.get("/v1/width/100/http://factor45.org/images/beach.jpg")
    open("beach.thumb.jpg", "wb") do |file|
        file.write(resp.body)
    end
end