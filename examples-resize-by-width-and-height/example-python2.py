import urllib2

param_operation1 = "height"
param_value1 = 100 # New height in pixels.

param_operation2 = "width"
param_value2 = 100 # New width in pixels.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

response = urllib2.urlopen("http://api.rethumb.com/v1/{0}/{1}/{2}/{3}/{4}".format(param_operation1, param_value1, param_operation2, param_value2, image_url))
fh = open(image_filename, "w")
fh.write(response.read())
fh.close()
