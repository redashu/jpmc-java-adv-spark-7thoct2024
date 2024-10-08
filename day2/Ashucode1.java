package com.example;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import java.util.List;
import java.util.Arrays;;

public class Ashucode1 {
    public static void main(String[] args) {
        // Step 1: Configure Spark connectin from java 
        SparkConf conf = new SparkConf().setAppName("ashudemoapp").setMaster("local[*]");
        // Step 2: Create a Java Spark Context
        JavaSparkContext sc = new JavaSparkContext(conf);
        // by using sc we can connect to spark and createRDD use tf and action
        // creating array 
        // Step 3: Create a list of data
        // List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<String> data = Arrays.asList("ashutoshh","kavia","suneela","narendra");
        // creating RDD 1
        JavaRDD<String>  RDD1 = sc.parallelize(data);
        // creating RDD2 after apply map transformat in RDD1 
        JavaRDD<String>  RDD2 = RDD1.map(x -> x.toUpperCase());
        // action function 
        // Step 6: Print the results
        List<String> output = RDD2.collect();
        System.out.println("uppaser case string:  "+output);
        //RDD2.collect().forEach(System.out::println);
        // closing context 
        sc.stop();
    }
}