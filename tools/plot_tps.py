import matplotlib.pyplot as plt
import numpy as np
import os

plt.figure(1)

titles = { "221" : "jetty", "222" : "netty", "223" : "tomcat", "224" : "undertow"}

for key, value in titles.iteritems():
	plt.subplot(key)
	plt.title(value)
	plt.grid(True)

x = [1, 25, 50, 100, 200, 400, 800, 1600, 3200, 6400, 10000]	
plot2subplot = { "jetty-async" : 221, "dropwizard" : 221, "jetty" : 221, "spark" : 221, "ninja-framework" : 221, "restx" : 221, 
				 "netty4-http-server" : 222, "wso2msf4j" : 222, "vertx" : 222, "rest-express" : 222, 
				 "springboot" : 223, "tomcat" : 223, 
				 "undertow" : 224, "wildfly-swarm" : 224 }

def plot_file(fileName):
	y = []
	with open(fileName, 'r' ) as t:
		for line in t:
			if ( line.startswith("Average for concurrency level")):
				items = line.split(" ")
				y.append(items[6])
	fileName = fileName.split("/")[0]
	plt.subplot(plot2subplot[fileName])
	plt.plot(x,y, label=fileName)
	plt.legend()

# traverse root directory, and list directories as dirs and files as files
for root, dirs, files in os.walk("."):
    for file in files:
        if ( file == "tps.txt" ):
        	plot_file(os.path.basename(root) + "/" + file)

plt.xlabel('concurrency')
plt.ylabel('req/sec')
#plt.xticks(np.arange(min(x), max(x)+1, 100))
plt.legend()
plt.show()
