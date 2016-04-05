import urllib2

param_operation = "cover"
param_value = "150x200" # New WIDTHxHEIGHT in pixels.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

response = urllib2.urlopen("http://api.rethumb.com/v1/{0}/{1}/{2}".format(param_operation, param_value, image_url))
fh = open(image_filename, "w")
fh.write(response.read())
fh.close()