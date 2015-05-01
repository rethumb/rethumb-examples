from urllib.request import urlopen
import json

response = urlopen("http://api.rethumb.com/v1/exif/all/http://images.rethumb.com/image_exif_1.jpg")

json_data = json.loads(response.read().decode("utf-8"))

print(json_data)