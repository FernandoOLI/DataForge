import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

trait SparkTestBase extends AnyFunSuite with BeforeAndAfterEach {

  lazy val spark: SparkSession = SparkSession.builder()
    .appName("test")
    .master("local[2]")
    .config("spark.sql.shuffle.partitions", "1")
    .getOrCreate()

  override def afterEach(): Unit = {
    if (spark != null) {
      spark.stop()
    }
    super.afterEach()
  }
}