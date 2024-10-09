package com.example;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.api.java.JavaRDD;
import java.util.Arrays;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;


public class Demosql1 {
    public static void main(String[] args) {
        // Spark configuration
        SparkConf conf = new SparkConf().setAppName("BasicDataFrame").setMaster("local[*]");

        // Create a SparkSession
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        // Create a JavaSparkContext
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        // Create a JavaRDD from a basic array
        String[] data = new String[]{"John", "Doe", "Alice", "Smith","ashu"};
        JavaRDD<Row> rdd = jsc.parallelize(Arrays.asList(data))
                .map(name -> RowFactory.create(name));

        // Define the schema for the DataFrame
        StructType schema = new StructType().add("name", DataTypes.StringType);

        // Convert the RDD to a DataFrame
        Dataset<Row> df = spark.createDataFrame(rdd, schema);

        // Show the DataFrame
        df.show();

        // Stop the SparkSession
        spark.stop();
        
    }
}