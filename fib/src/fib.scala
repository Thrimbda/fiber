import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel._

object Main {

  def fib(n: Long): Long = {
    if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 1) + fib(n - 2)
  }

  def main(args: Array[String]): Unit = {
    val parList = (0L to 99L).toList.par
    parList.foreach { n =>
      println(s"fib for number $n is: ${fib(n)}")
    }
  }

}
