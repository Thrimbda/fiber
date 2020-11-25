import java.util.concurrent.ForkJoinPool

import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel._

object Fib {

  def fib(n: Long): Long = {
    if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 1) + fib(n - 2)
  }

  sealed trait Result
  case class InvalidOpt(opt: String) extends Result
  case class Parsed(jobs: Int) extends Result
  case object Help extends Result

  val usage: String =
    """Fiber core!
      |
      |usage: Fib [-h] --jobs <num_job>
      |
      |enjoy.
      |""".stripMargin

  def argParse(args: List[String]): Result = {
    @tailrec
    def parseLeft(parsed: Map[String, String], left: List[String]): Either[String, Map[String, String]] = {
      left match {
        case "-h" :: _ => Right(Map.empty)
        case "--job" :: jobs :: tail => parseLeft(parsed.updated("job", jobs), tail)
        case Nil => Right(parsed)
        case opt :: _ => Left(opt)
      }
    }
    val result = parseLeft(Map.empty, args)
    result match {
      case Left(value) => InvalidOpt(value)
      case Right(value) =>
        if (value.isEmpty) Help
        else Parsed(value("job").toInt)
    }
  }

  def main(args: Array[String]): Unit = {
    val parsed = argParse(args.toList)
    parsed match {
      case Help =>
        println(usage)
      case InvalidOpt(opt) =>
        println(s"invalid option: $opt")
        println(usage)
        sys.exit(128)
      case Parsed(jobs) =>
        val parList = (0L to 99L).toList.par
        parList.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(jobs))
        parList.foreach { n =>
          println(s"fib for number $n is: ${fib(n)}")
        }
    }
  }

}
