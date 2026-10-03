#!/usr/bin/python3

import urllib.request, urllib.parse, urllib.error
import json

# https://developers.google.com/maps/documentation/geocoding/intro
serviceurl = 'https://maps.googleapis.com/maps/api/geocode/json?'

key = '&key=AIzaSyD6y6HbH1TXm-Calw2rui0IM6Gs7AEQ7Vg'

while True:
    address = input('Enter location: ')
    if len(address) < 1: break

    url = serviceurl + urllib.parse.urlencode({'address': address}) + key

    print('Retrieving', url)
    uh = urllib.request.urlopen(url)
    data = uh.read().decode()
    print('Retrieved', len(data), 'characters')

    # Raw data
    #print(data)

    try:
        js = json.loads(data)
    except:
        js = None

    if not js or 'status' not in js or js['status'] != 'OK':
        print('==== Failure To Retrieve ====')
        print(data)
        continue

    lat = js["results"][0]["geometry"]["location"]["lat"]
    lng = js["results"][0]["geometry"]["location"]["lng"]
    print('lat', lat, 'lng', lng)
    location = js['results'][0]['formatted_address']
    print(location)
    county = js["results"][0]['address_components'][1]['short_name']
    print(county)
