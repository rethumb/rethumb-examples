from urllib.request import urlopen

response = urlopen("http://api.rethumb.com/v1/width/100/http://factor45.org/images/beach.jpg")
fh = open("beach.thumb.jpg", "wb")
fh.write(response.read())
fh.close()