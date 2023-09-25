package section4_effectful

import fs2._
import cats.effect._
import scala.concurrent.duration._

object Timed extends IOApp.Simple {
  override def run: IO[Unit] = {
    val drinkWater = Stream.iterateEval(1)(n => IO.println("Drink more water").as(n+1))

    drinkWater.compile.drain
    drinkWater.timeout(1.second).compile.drain // Times out after one second and the execution ends with an exception
    drinkWater.interruptAfter(1.second).compile.drain // Times out after one second and the execution ends the execution gracefully
    drinkWater.delayBy(2.seconds).interruptAfter(4.seconds).compile.drain // Delay by two seconds and then run for two more seconds

  }
}