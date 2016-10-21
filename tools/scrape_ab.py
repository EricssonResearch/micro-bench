# concurrency level is not needed, timestamp is needed though, also a print out per test, not just the last one

import sys

concurrency = ""
completeRequests = ""
failedRequests = ""
keepAlive = ""
tps = ""

fileName = sys.argv[1]

with open(fileName, "r") as f:
	print "concurrency\tcompletedRequests\tfailedRequests\tkeepAlive\ttps"
	for line in f:
		if line.startswith("Concurrency Level:"):
			c = line.strip().split(":")[1]
			if c != concurrency:
				if concurrency != "" or concurrency == c:
					print concurrency + "\t" + completeRequests + "\t" + failedRequests + "\t" +  keepAlive + "\t" + tps
				concurrency = str(c.strip())
				completeRequests = ""
				failedRequests = ""
				keepAlive = ""
				tps = ""
			continue

		if line.startswith("Complete requests:"):
			completeRequests = str(line.strip().split(":")[1].strip())
			continue

		if line.startswith("Failed requests:"):
			failedRequests = str(line.strip().split(":")[1].strip())
			continue

		if line.startswith("Keep-Alive requests:"):
			keepAlive = str(line.strip().split(":")[1].strip())
			continue

		if line.startswith("Requests per second: "):
			tps = str(line.strip().split(" ")[6]).replace(".",",")
			continue

# print the last line - let's not forget that
print concurrency + "\t" + completeRequests + "\t" + failedRequests + "\t" +  keepAlive + "\t" + tps
