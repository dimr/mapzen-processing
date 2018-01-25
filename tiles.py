import requests
#import requests_cache
import json
#requests_cache.install_cache('tiles_cache')

#http://vector.mapzen.com/osm/buildings/13/1310/3166.json?api_key=vector-tiles-PADQnWp
url='http://vector.mapzen.com/osm/buildings/13/{long}/{lat}.json?api_key=vector-tiles-PADQnWp'

center=url.format(long=1310,lat=3166)
left=url.format(long=1309,lat=3166)
top_left_corner=url.format(long=1309,lat=3165)
top=url.format(long=1310,lat=3165)
top_rigth_corner=url.format(long=1311,lat=3165)
right=url.format(long=1311,lat=3166)
bottom=url.format(long=1311,lat=3167)
bottom_left_corner=url.format(long=1309,lat=3167)


tiles=[]
tiles.extend([center,left,top_left_corner,top,top_rigth_corner,right,bottom,bottom_left_corner])


data=[]
for url in tiles:
    r=requests.get(url)
    print(r.url)
    #print(r.json())
    #print len(r.json()['features'])
    for i in r.json()['features']:
        data.append(json.dumps(i)+'\n')
        #print i['properties'].get('addr_street','-----')
f=open('data.json','w')
for i in data:
    f.write(i)

#print len(data)
print(data[0])
