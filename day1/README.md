# apache spark 

### data problem understanding -- storage & processing 

<img src="datapr1.png">

### the distributed storage intro 

<img src="st1.png">

### Introduction hadoop framework -- HDFS 

<img src="hdfs1.png">

## Distributed process by hadoop framework project -- MR \ YARN 

<img src="mr.png">

## Introducing apache spark 

<img src="spark1.png">

## key components 

<img src="spark2.png">

## apache spark 3T architecture 

<img src="spark3t.png">

### 3 type of setup in apache spark we can do 

<img src="sparksetup.png">

## setup spark stand alone in single linux machine 

### login with root / admin user 

```
ec2-user@ip-172-31-33-162 ~]$ sudo -i
[root@ip-172-31-33-162 ~]# whoami
root
[root@ip-172-31-33-162 ~]# 

```

### changing hostname for better identification 

```
 hostnamectl set-hostname  ashu-spark-machine 
[root@ip-172-31-33-162 ~]# exit
logout
[ec2-user@ip-172-31-33-162 ~]$ sudo -i
[root@ashu-spark-machine ~]# 

```
### setup of jdk 

<img src="setupjdk.png">

### Installing jdk 17 in RHEL /amaon linux / oracle linux 

```
dnf install java-17*

```

### verify 

```
java --version 
openjdk 17.0.12 2024-07-16 LTS
OpenJDK Runtime Environment Corretto-17.0.12.7.1 (build 17.0.12+7-LTS)
OpenJDK 64-Bit Server VM Corretto-17.0.12.7.1 (build 17.0.12+7-LTS, mixed mode, sharing)

===>

[root@ashu-spark-machine ~]# jps
27069 Jps

```

### Download spark 3.x bin 

[click_here](https://spark.apache.org/downloads.html)

```
 wget https://dlcdn.apache.org/spark/spark-3.5.3/spark-3.5.3-bin-hadoop3.tgz


--2024-10-07 07:22:53--  https://dlcdn.apache.org/spark/spark-3.5.3/spark-3.5.3-bin-hadoop3.tgz
Resolving dlcdn.apache.org (dlcdn.apache.org)... 151.101.2.132, 2a04:4e42::644
Connecting to dlcdn.apache.org (dlcdn.apache.org)|151.101.2.132|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 400864419 (382M) [application/x-gzip]
Saving to: ‘spark-3.5.3-bin-hadoop3.tgz’

spark-3.5.3-bin-hadoop3.tgz        100%[=============================================================>] 382.29M   119MB/s    in 3.2s    

2024-10-07 07:22:57 (120 MB/s) - ‘spark-3.5.3-bin-hadoop3.tgz’ saved [400864419/400864419]


====>>

[root@ashu-spark-machine ~]# ls
spark-3.5.3-bin-hadoop3.tgz
[root@ashu-spark-machine ~]# 

```

### Extract to bins

```
tar xvzf spark-3.5.3-bin-hadoop3.tgz 

```

### copy to some directory i choose /opt

```
 mv spark-3.5.3-bin-hadoop3 /opt/spark35

[root@ashu-spark-machine ~]# cd /opt/spark35/

[root@ashu-spark-machine spark35]# ls
LICENSE  NOTICE  R  README.md  RELEASE  bin  conf  data  examples  jars  kubernetes  licenses  python  sbin  yarn
[root@ashu-spark-machine spark35]# 

```

### conf directory 

```
cd conf/
[root@ashu-spark-machine conf]# ls
fairscheduler.xml.template  metrics.properties.template   spark-env.sh.template
log4j2.properties.template  spark-defaults.conf.template  workers.template


[root@ashu-spark-machine conf]# cp spark-defaults.conf.template  spark-defaults.conf
[root@ashu-spark-machine conf]# ls
fairscheduler.xml.template  metrics.properties.template  spark-defaults.conf.template  workers.template
log4j2.properties.template  spark-defaults.conf          spark-env.sh.template


[root@ashu-spark-machine conf]# cat spark-defaults.conf
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Default system properties included when running spark-submit.
# This is useful for setting default environmental settings.

# Example:
# spark.master                     spark://master:7077
# spark.eventLog.enabled           true
# spark.eventLog.dir               hdfs://namenode:8021/directory

```

## Interaction with spark 

- **live** 
- **jobs** 

### Interaction with scala interface 

```
cd /opt/spark35/
[root@ashu-spark-machine spark35]# ls
LICENSE  NOTICE  R  README.md  RELEASE  bin  conf  data  examples  jars  kubernetes  licenses  python  sbin  yarn
[root@ashu-spark-machine spark35]# 

[root@ashu-spark-machine spark35]# ./bin/spark-shell 
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
24/10/07 07:40:34 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://ip-172-31-33-162.ap-south-1.compute.internal:4040
Spark context available as 'sc' (master = local[*], app id = local-1728286835677).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.5.3
      /_/
         
Using Scala version 2.12.18 (OpenJDK 64-Bit Server VM, Java 17.0.12)
Type in expressions to have them evaluated.
Type :help for more information.

scala> 

```

### for easy admin / dev practise setup ENV 

```
root@ashu-spark-machine spark35]# SPARK_HOME=/opt/spark35/
[root@ashu-spark-machine spark35]# PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin
[root@ashu-spark-machine spark35]# export PATH 
[root@ashu-spark-machine spark35]# 

```

### to make it permanent 

```
[root@ashu-spark-machine spark35]# echo SPARK_HOME=/opt/spark35/ >>~/.bashrc 
[root@ashu-spark-machine spark35]# 
[root@ashu-spark-machine spark35]# echo PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin >>~/.bashrc 

[root@ashu-spark-machine spark35]# echo export PATH   >>~/.bashrc 
[root@ashu-spark-machine spark35]# 
```

### close session of machine and relogin to verify

```
 sudo -i
[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# spark-shell 
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
24/10/07 07:46:59 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://ip-172-31-33-162.ap-south-1.compute.internal:4040
Spark context available as 'sc' (master = local[*], app id = local-1728287220378).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.5.3
      /_/
         
Using Scala version 2.12.18 (OpenJDK 64-Bit Server VM, Java 17.0.12)
Type in expressions to have them evaluated.
Type :help for more information.

scala> :quit

```

## starting master 

```
start-master.sh 
starting org.apache.spark.deploy.master.Master, logging to /opt/spark35/logs/spark-root-org.apache.spark.deploy.master.Master-1-ashu-spark-machine.out

```

### verify port 

```

netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      2315/sshd: /usr/sbi 
tcp6       0      0 :::22                   :::*                    LISTEN      2315/sshd: /usr/sbi 
tcp6       0      0 :::8080                 :::*                    LISTEN      29090/java          
tcp6       0      0 172.31.33.162:7077      :::*                    LISTEN      29090/java          
[root@ashu-spark-machine ~]# 


```

### starting worker 

```
 start-worker.sh   spark://172.31.33.162:7077
starting org.apache.spark.deploy.worker.Worker, logging to /opt/spark35/logs/spark-root-org.apache.spark.deploy.worker.Worker-1-ashu-spark-machine.out

```

### ports in spark components 

<img src="port1.png">

## spark RDD concept 

<img src="sparkrdd.png">

## spark shell with scala 

```
val  ashu_numbers = Seq(1,3,6,8,10)
ashu_numbers: Seq[Int] = List(1, 3, 6, 8, 10)



scala> val rdd1 = sc.parallelize(ashu_numbers)
rdd1: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[0] at parallelize at <console>:24

scala> val rdd2 = rdd1.map(x => x*5)
rdd2: org.apache.spark.rdd.RDD[Int] = MapPartitionsRDD[1] at map at <console>:23

scala> val result = rdd2.collect()
result: Array[Int] = Array(5, 15, 30, 40, 50)

```

### stopping spark master and worker in single node 

```
oot@ashu-spark-machine ~]# stop-worker.sh 
stopping org.apache.spark.deploy.worker.Worker

[root@ashu-spark-machine ~]# stop-master.sh 
stopping org.apache.spark.deploy.master.Master

[root@ashu-spark-machine ~]# 
[root@ashu-spark-machine ~]# netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      2315/sshd: /usr/sbi 
tcp6       0      0 :::22                   :::*                    LISTEN      2315/sshd: /usr/sbi 
[root@ashu-spark-machine ~]# 

```
## spark as container 

<img src="sparkc1.png">

## Installing docker engine 

```
 dnf install docker 
Last metadata expiration check: 3:36:23 ago on Mon Oct  7 07:01:02 2024.
Dependencies resolved.
=========================================================================================================================================
 Package                                Architecture           Version                                 Repository                   Size
=========================================================================================================================================
Installing:
 docker                                 x86_64                 25.0.6-1.amzn2023.0.2                   amazonlinux                  44 M
Installing dependencies:
 containerd                             x86_64                 1.7.20-1.amzn2023.0.1                   amazonlinux                  35 M
 iptables-libs                          x86_64                 1.8.8-3.amzn2023.0.2

```

### starting docker engine 

```
 systemctl enable --now docker

```

### checking connection with docker engine 

```
docker version 
Client:
 Version:           25.0.5
 API version:       1.44
 Go version:        go1.22.5
 Git commit:        5dc9bcc
 Built:             Wed Aug 21 00:00:00 2024
 OS/Arch:           linux/amd64
 Context:           default

Server:
 Engine:
  Version:          25.0.6
  API version:      1.44 (minimum version 1.24)
  Go version:       go1.22.5
  Git commit:       b08a51f
  Built:            Wed Aug 21 00:00:00 2024
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.7.20
  GitCommit:        8fc6bcff51318944179630522a095cc9dbf9f353
 runc:
```

### Docker compose for handling multiple containers 

<img src="compose1.png">

### Installing compsoe in same machine 

```
 curl -SL https://github.com/docker/compose/releases/download/v2.29.6/docker-compose-linux-x86_64 -o  /usr/bin/docker-compose 

 ===
 chmod +x /usr/bin/docker-compose

 ===> verify 

 docker-compose version 
Docker Compose version v2.29.6

```

### creating a folder and compose.yaml inside it 

```
mkdir ashu_spark 
[root@ashu-spark-machine ~]# cd ashu_spark/
[root@ashu-spark-machine ashu_spark]# touch compose.yaml 
[root@ashu-spark-machine ashu_spark]# ls
compose.yaml
[root@ashu-spark-machine ashu_spark]# 

```

### running compsoe.yaml file 

```
docker-compose  up -d
[+] Running 7/10
[+] Running 10/10] 3.624MB / 3.624MB Pulling                                                                                        5.6s 
 ✔ ashu-driver Pulled                                                                                                               5.6s 
   ✔ 43c4264eed91 Pull complete                                                                                                     2.6s 
 ✔ ashu-master Pulled                                                                                                               7.7s 
   ✔ 302e3ee49805 Pull complete                                                                                                     3.0s 
   ✔ d07412f52e9d Pull complete                                                                                                     4.5s 
   ✔ 9ab66c386e9c Pull complete                                                                                                     4.6s 
   ✔ 4b563e5e980a Pull complete                                                                                                     4.6s 
   ✔ 55af3c8febf2 Pull complete                                                                                                     4.6s 
   ✔ 5b8e768fb22d Pull complete                                                                                                     4.6s 
   ✔ 85177e2c6f39 Pull complete                                                                                                     4.6s 
[+] Running 3/3
 ✔ Network ashu_spark_default  Created                                                                                              0.1s 
 ✔ Container ashuc1            Started                                                                                              3.0s 
 ✔ Container ashuc2            Started                 
 
                                                                              3.1s 
[root@ashu-spark-machine ashu_spark]# docker-compose ps
NAME      IMAGE     COMMAND                  SERVICE       CREATED         STATUS         PORTS
ashuc1    alpine    "ping fb.com"            ashu-driver   7 seconds ago   Up 3 seconds   
ashuc2    nginx     "/docker-entrypoint.…"   ashu-master   7 seconds ago   Up 3 seconds   0.0.0.0:1234->80/tcp, [::]:1234->80/tcp

```

### some compsoe commands 

```
 72  docker-compose  up -d
   73  docker-compose ps
   74  docker-compose stop 
   75  docker-compose start
   76  docker-compose down 

```

### spark is having a single common bin software 

<img src="cm1.png">

### compose as spark machines 

```
docker-compose  up -d
WARN[0000] /root/ashu_spark/compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Running 4/4
 ✔ spark-client Pulled                                                                                                             43.1s 
   ✔ fc134cf461a8 Pull complete                                                                                                    39.4s 
 ✔ spark-master Pulled                                                                                                             43.1s 
 ✔ spark-worker Pulled                                                                                                             43.1s 
[+] Running 4/4
 ✔ Network ashu_spark_default           Created                                                                                     0.1s 
 ✔ Container ashu_spark-spark-master-1  Started                                                                                     5.4s 
 ✔ Container ashu_spark-spark-worker-1  Started                                                                                     5.5s 
 ✔ Container ashu_spark-spark-client-1  Started                                                                                     5.3s 
[root@ashu-spark-machine ashu_spark]# docker-compose  ps
WARN[0000] /root/ashu_spark/compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
NAME                        IMAGE                         COMMAND                  SERVICE        CREATED          STATUS          PORTS
ashu_spark-spark-client-1   docker.io/bitnami/spark:3.5   "/opt/bitnami/script…"   spark-client   15 seconds ago   Up 10 seconds   
ashu_spark-spark-master-1   docker.io/bitnami/spark:3.5   "/opt/bitnami/script…"   spark-master   15 seconds ago   Up 10 seconds   0.0.0.0:7077->7077/tcp, :::7077->7077/tcp, 0.0.0.0:8080->8080/tcp, :::8080->8080/tcp
ashu_spark-spark-worker-1   docker.io/bitnami/spark:3.5   "/opt/bitnami/script…"   spark-worker   15 seconds ago   Up 10 seconds   0.0.0.0:4040->4040/tcp, :::4040->4040/tcp, 0.0.0.0:8081->8081/tcp, :::8081->8081/tcp

```

## login to spark driver machine 

```
docker-compose  exec -it spark-client bash 
WARN[0000] /root/ashu_spark/compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
I have no name!@77fbde744bf3:/opt/bitnami/spark$ 
I have no name!@77fbde744bf3:/opt/bitnami/spark$ 
I have no name!@77fbde744bf3:/opt/bitnami/spark$ exit
exit

```

## java apps with apache maven 

<img src="mvn.png">

### setup of maven with jdk 11 support in linux 

```
dnf install java-11* 

===> installing apache maven 3.x 
dnf install maven 

```
### verify maven installation 

```
 mvn -v
Apache Maven 3.8.4 (Red Hat 3.8.4-3.amzn2023.0.5)
Maven home: /usr/share/maven
Java version: 17.0.12, vendor: Amazon.com Inc., runtime: /usr/lib/jvm/java-17-amazon-corretto.x86_64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.1.109-118.189.amzn2023.x86_64", arch: "amd64", family: "unix"
[root@ashu-spark-machine ashu_spark]# 


```
