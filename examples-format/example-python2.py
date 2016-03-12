import urllib2

# First apply a square operation, then convert to PNG format.

param_operation1 = "square"
param_value1 = 100

param_operation2 = "format"
param_value2 = "png" # Other formats available: jpg, gif, tiff and webp.

image_url = "http://images.rethumb.com/image_coimbra_600x300.jpg"
image_filename = "resized-image.jpg"

response = urllib2.urlopen("http://api.rethumb.com/v1/{0}/{1}/{2}/{3}/{4}".format(param_operation1, param_value1, param_operation2, param_value2, image_url))
fh = open(image_filename, "w")
fh.write(response.read())
fh.close()
