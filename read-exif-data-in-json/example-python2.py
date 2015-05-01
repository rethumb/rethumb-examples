import urllib2
import json

response = urllib2.urlopen("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

json_data = json.loads(response.read())

print json_data