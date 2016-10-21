from datetime import datetime
import sys

concurrency_level = -1
start_time = -1
end_time = -1
count = 0

if len(sys.argv) < 2:
		print "please provide 2 arguments, 1st is the mem file, 2nd is the tps file"
		exit()

thread_file = sys.argv[1]
tps_file = sys.argv[2]

print "Using", thread_file, "and", tps_file

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
					with open(thread_file, 'r') as m:
						average = 0
						items = 0
						for other in m:
							terms = other.strip().split(" ")
							stamp = datetime.strptime(terms[0], "%H:%M:%S")
							# copy input from previous - it's probably better if threads used a better timestamp
							stamp = stamp.replace(year=start_time.year, month=start_time.month, day=start_time.day)
							if (stamp >= start_time and stamp <= end_time):
								threads = terms[1]
								average += int(threads)
								items +=1
					if (items>0):
						print "processing for:", concurrency_level, start_time, end_time, (average/items)
						#print average/items
					count = 0
				else:
					count += 1