from pyspark  import SparkConf , SparkContext
import time 
# initialize context 
config_spark = SparkConf().setAppName("ashuwc").setMaster("local[*]")
# creating sc 
sc = SparkContext(conf=config_spark)
# using sc we can RDD or DF 

data = sc.textFile("file:///home/ec2-user/ashu_codes/python_codes/data.txt")

lines = data.flatMap( lambda line: line.split())
word_pairs = lines.map(lambda word: (word, 1))
wc = word_pairs.reduceByKey(lambda x ,y : x + y )
output = wc.collect()

for word,count in output:
    print(f"{word}: {count}")
    time.sleep(1)