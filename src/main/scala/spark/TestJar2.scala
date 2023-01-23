package spark
import org.apache.spark.sql.SparkSession
object TestJar2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .getOrCreate();
 println("session got created")
    val sc = spark.sparkContext
    val data = sc.parallelize(Seq(1,2,3,4))
    println("printing value of data")
    println(data.collect().foreach(print))

    val df = spark.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .load("/tmp/ec2-user/customers.csv");
    println("printing value of data frame")
    println(df.show())
    // This import is needed to use the $-notation
    import spark.implicits._
    // Print the schema in a tree format
    println(df.printSchema())
    println("printing value of data schema")

  }

}
