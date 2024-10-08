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
