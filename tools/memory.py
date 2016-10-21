from datetime import datetime
import sys

concurrency_level = -1
start_time = -1
end_time = -1
count = 0

if len(sys.argv) < 2:
	print "please provide 2 arguments, 1st is the mem file, 2nd is the tps file"
	exit()

mem_file = sys.argv[1] 
tps_file = sys.argv[2]

print "Using", mem_file, "and", tps_file

with open(tps_file, 'r') as t:
	for line in t:
		if line.startswith("start concurrency ") and count == 0:
			terms = line.strip().split(" ")
			concurrency_level = terms[2]
			start_time = datetime.strptime(terms[9] + "-" + terms[5] + "-" + terms[6] + " " + terms[7], '%Y-%b-%d %H:%M:%S')
		elif line.startswith("end concurrency "):
			if count == 2:
				terms = line.strip().split(" ")
				end_time = datetime.strptime(terms[9] + "-" + terms[5] + "-" + terms[6] + " " + terms[7], '%Y-%b-%d %H:%M:%S')

				# open second file
				with open(mem_file, 'r') as m:
					# skip the first 3 lines
					average = 0
					items = 0
					for i in range(3):
						m.readline()
					for other in m:
						terms = other.strip().split(" ")
						stamp = datetime.strptime(terms[0][0:23], "%Y-%m-%dT%H:%M:%S.%f")
						if ( terms[3]=="(Allocation" and (stamp >= start_time and stamp <= end_time)):
								k = terms[6].find("(")
								l = terms[6].find(")")
								mem = terms[6][k+1:l-1]
								average += int(mem)
								items +=1
				if (items>0):
					#print "processing for:", concurrency_level, start_time, end_time, (average/items)
					print (average/items)/1000000 #convert to GB
				count = 0
			else:
				count += 1
