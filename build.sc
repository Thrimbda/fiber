// build.sc
import mill._, scalalib._

object fib extends ScalaModule {
  def scalaVersion = "2.13.3"
  def ivyDeps = Agg(
      ivy"org.scala-lang.modules::scala-parallel-collections:0.2.0"
  )
}