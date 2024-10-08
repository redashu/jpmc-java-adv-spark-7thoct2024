## spark revision 

<img src="rev1.png">

### RDD , DAG , lazy evalution 

<img src="rev2.png">

## RDD concepts and partitions of it 

<img src="rdd1.png">

### RDD transformation types 

<img src="rdd2.png">

### verify spark_home variable 

```
ec2-user@ashu-spark-machine ~]$ sudo -i
[root@ashu-spark-machine ~]# echo $SPARK_HOME
/opt/spark35/
[root@ashu-spark-machine ~]# 

```
## start spark master and worker 

```
root@ashu-spark-machine ~]# start-master.sh 
org.apache.spark.deploy.master.Master running as process 4046.  Stop it first.

[root@ashu-spark-machine ~]# start-worker.sh   spark://172.31.33.162:7077
org.apache.spark.deploy.worker.Worker running as process 4130.  Stop it first.

[root@ashu-spark-machine ~]# netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      2253/sshd: /usr/sbi 
tcp        0      0 127.0.0.1:36325         0.0.0.0:*               LISTEN      2088/containerd     
tcp6       0      0 172.31.33.162:7077      :::*                    LISTEN      4046/java           
tcp6       0      0 :::22                   :::*                    LISTEN      2253/sshd: /usr/sbi 
tcp6       0      0 :::8080                 :::*                    LISTEN      4046/java           
tcp6       0      0 :::8081                 :::*                    LISTEN      4130/java           
tcp6       0      0 172.31.33.162:42199     :::*                    LISTEN      4130/java       

```

### allow ssh with password 

```
 passwd ec2-user
Changing password for user ec2-user.
New password: 
BAD PASSWORD: The password is shorter than 8 characters
Retype new password: 
passwd: all authentication tokens updated successfully.
[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# nano /etc/ssh/sshd_config 
[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# systemctl restart sshd 
[root@ashu-spark-machine ~]# 

```

### giving acess to ec2-user of apache spark 

```
[ec2-user@ashu-spark-machine ~]$ echo SPARK_HOME=/opt/spark35/ >>~/.bashrc 
[ec2-user@ashu-spark-machine ~]$ echo PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin >>~/.bashrc 
[ec2-user@ashu-spark-machine ~]$ echo export PATH   >>~/.bashrc 
[ec2-user@ashu-spark-machine ~]$ 
[ec2-user@ashu-spark-machine ~]$ source  ~/.bashrc 
[ec2-user@ashu-spark-machine ~]$ 

```
### sample RDD transformation and action  in scala 

```
Using Scala version 2.12.18 (OpenJDK 64-Bit Server VM, Java 17.0.12)
Type in expressions to have them evaluated.
Type :help for more information.

scala> val  ashu_numbers = Seq(1,3,6,8,10)
ashu_numbers: Seq[Int] = List(1, 3, 6, 8, 10)

scala> val rdd1 = sc.parallelize(ashu_numbers)
rdd1: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[0] at parallelize at <console>:24

scala> val rdd2 = rdd1.map(x => x*5)
rdd2: org.apache.spark.rdd.RDD[Int] = MapPartitionsRDD[1] at map at <console>:23

scala> val result = rdd2.collect()
result: Array[Int] = Array(5, 15, 30, 40, 50)


```

### lets do in java 

```

```