## Revision 

<img src="rev1.png">

### deployment modes in spark 

<img src="rev2.png">

### verify k8s connection 

```
kubectl  get nodes
NAME                              STATUS   ROLES    AGE    VERSION
ip-192-168-101-209.ec2.internal   Ready    <none>   104m   v1.29.8-eks-a737599
ip-192-168-113-166.ec2.internal   Ready    <none>   104m   v1.29.8-eks-a737599
ip-192-168-19-235.ec2.internal    Ready    <none>   2d3h   v1.29.8-eks-a737599
ip-192-168-51-6.ec2.internal      Ready    <none>   104m   v1.29.8-eks-a737599
ip-192-168-57-194.ec2.internal    Ready    <none>   26h    v1.29.8-eks-a737599
ip-192-168-65-27.ec2.internal     Ready    <none>   104m   v1.29.8-eks-a737599
ip-192-168-8-27.ec2.internal      Ready    <none>   104m   v1.29.8-eks-a737599
ip-192-168-88-127.ec2.internal    Ready    <none>   26h    v1.29.8-eks-a737599
[ec2-user@ashu-spark-machine ~]$ 

```

### in k8s spark is deployed as Statefulsets 

```
kubectl  get  sts
NAME                READY   AGE
jpmc-spark-master   1/1     18h
jpmc-spark-worker   8/8     18h
[ec2-user@ashu-spark-machine ~]$ 

```

### login to any pod to run spark-submit 

```
 kubectl  exec -it jpmc-spark-worker-0 -- bash 
I have no name!@jpmc-spark-worker-0:/opt/bitnami/spark$ 

===> cluster mode command 

```
spark-submit   --master spark://jpmc-spark-master-svc:7077 --deploy-mode cluster 
 --class org.apache.spark.examples.SparkPi examples/jars/spark-examples_2.12-3.5.1.jar 10 

 
24/10/09 05:36:45 INFO SecurityManager: Changing view acls to: spark
24/10/09 05:36:45 INFO SecurityManager: Changing modify acls to: spark
24/10/09 05:36:45 INFO SecurityManager: Changing view acls groups to: 
24/10/09 05:36:45 INFO SecurityManager: Changing modify acls groups to: 
24/10/09 05:36:45 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users with view permissions: spark; groups with view permissions: EMPTY; users with modify permissions: spark; groups with modify permissions: EMPTY
24/10/09 05:36:45 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
24/10/09 05:36:46 INFO Utils: Successfully started service 'driverClient' on port 41079.
24/10/09 05:36:46 INFO TransportClientFactory: Successfully created connection to jpmc-spark-master-svc/10.100.145.116:7077 after 95 ms (0 ms spent in bootstraps)
24/10/09 05:36:47 INFO ClientEndpoint: ... waiting before polling master for driver state
24/10/09 05:36:47 INFO ClientEndpoint: Driver successfully submitted as driver-20241009053647-0002
24/10/09 05:36:52 INFO ClientEndpoint: State of driver-20241009053647-0002 is RUNNING
24/10/09 05:36:52 INFO ClientEndpoint: Driver running on 192.168.73.250:41027 (worker-20241009034737-192.168.73.250-41027)
24/10/09 05:36:52 INFO ClientEndpoint: spark-submit not configured to wait for completion, exiting spark-submit JVM.
24/10/09 05:36:52 INFO ShutdownHookManager: Shutdown hook called
24/10/09 05:36:52 INFO ShutdownHookManager: Deleting directory /tmp/spark-aaf5011e-c6aa-4ee2-becf-571628e9e05f

```
