import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel._

object Main {

  def fib(n: Long): Long = {
    if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 1) + fib(n - 2)
  }

  def main(args: Array[String]): Unit = {
    val parList = (40L to 80L).toList.par
    parList.tasksupport = new ForkJoinTaskSupport(
      new java.util.concurrent.ForkJoinPool(16)
    )
    val meaningless = parList.map(fib)
    println(meaningless)
  }

}
