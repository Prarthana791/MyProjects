# -*- coding: utf-8 -*-
"""
Created on Wed Mar  1 17:19:05 2017

@author: sushma
"""

import sys
import urllib
import urllib.request as ur
import time
import datetime
import json
import webbrowser
import csv
import codecs

 
ACCESS_URL = "https://graph.facebook.com/"
 
def collect_data():	
    k=0
    print("\n\n")
    print(" #############################################_________________Facebook API information access System____________________#############################################")
    webbrowser.open("https://www.facebook.com/dialog/oauth?"
                    "response_type=token&client_id=332703427131473&"
                    "redirect_uri=http://developers.facebook.com/tools/"
                    "explorer/callback&scope=user_birthday,user_friends,publish_actions"
                    ",read_stream")
    
    webbrowser.open("http://developers.facebook.com/tools/explorer")
    ACCESS_TOKEN = input("\nEnter the TOKEN string obtained from API "
                           "explorer page: \n")
   
    print("\n\n\n")
    print("Downloading Users Information")
   	
    while(k<50):  
        datafile = ur.urlopen(ACCESS_URL + 'me?fields=friends.fields(hometown)&access_token='+ACCESS_TOKEN)
        df=ur.urlopen(ACCESS_URL + 'me?fields=friends&access_token='+ACCESS_TOKEN)  
            
        if 'hometown' in json.loads(datafile.read().decode('utf-8'))['friends']['data'][k]:
            	
            datafile.close()
            df.close()
            datafile = ur.urlopen(ACCESS_URL + 'me?fields=friends.fields(hometown)&access_token='+ACCESS_TOKEN)
            bb=json.loads(datafile.read().decode('utf-8'))['friends']['data'][k]['hometown']['name']
            ss=json.loads(datafile.read().decode('utf-8'))['friends']['data'][k]['name']
            print(ss)
            datafile.close()
            decode_address_to_coordinates(bb)             
        k=k+1
        sys.stdout.write("Download progress: %d /450   \r" % (k) )
        sys.stdout.flush()
        
    print("\nData download completed")
    input()
 
 
def decode_address_to_coordinates(address):
        params = {
                'address' : address,
                'sensor' : 'true',
        }  
        url = 'http://maps.google.com/maps/api/geocode/json?' + urllib.parse.urlencode(params)
        response = ur.urlopen(url)
        
        reader = codecs.getreader("utf-8")
        result = json.load(reader(response))
        '''result = json.load(response)'''
        log=result['results'][0]['geometry']['location']['lng']
        lat=result['results'][0]['geometry']['location']['lat']
        
        p='%f,%f'%(log,lat)
        myfile=open("test.csv", "a")
        myfile.write(p + "\n")
 
    
if __name__ == "__main__":
   collect_data()
