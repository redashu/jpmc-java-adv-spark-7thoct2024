from pyspark.ml.regression import LinearRegression
from pyspark.sql import SparkSession
from pyspark.ml.feature import VectorAssembler

# Initialize Spark session
spark = SparkSession.builder.appName("LinearRegressionExample").getOrCreate()

# Create a DataFrame with some sample data
data = [
    (1.0, 2.0),
    (2.0, 4.0),
    (3.0, 6.0),
    (4.0, 8.0),
    (5.0, 10.0)
]

# Define the schema for the DataFrame
columns = ["feature", "label"]

# Create DataFrame
df = spark.createDataFrame(data, columns)

# Feature transformation using VectorAssembler
assembler = VectorAssembler(inputCols=["feature"], outputCol="features")
transformed_data = assembler.transform(df)

# Split the data into training and testing sets
train_data, test_data = transformed_data.randomSplit([0.8, 0.2])

# Initialize the Linear Regression model
lr = LinearRegression(featuresCol="features", labelCol="label")

# Fit the model to the training data
lr_model = lr.fit(train_data)

# Print the coefficients and intercept for the linear regression model
print("Coefficients: " + str(lr_model.coefficients))
print("Intercept: " + str(lr_model.intercept))

# Make predictions on the test data
predictions = lr_model.transform(test_data)

# Select and display features, label, and prediction columns
predictions.select("features", "label", "prediction").show()

# Stop the Spark session
spark.stop()