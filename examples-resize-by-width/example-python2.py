import urllib2

width = 100; # New width in pixels.
image = "http://factor45.org/images/beach.jpg";
newImage = "beach.thumb.jpg";

response = urllib2.urlopen("http://api.rethumb.com/v1/width/{0}/{1}".format(width, image))
fh = open(newImage, "w")
fh.write(response.read())
fh.close()