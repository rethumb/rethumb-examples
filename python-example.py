import urllib2

response = urllib2.urlopen("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg")
fh = open("beach.thumb.jpg", "w")
fh.write(response.read())
fh.close()