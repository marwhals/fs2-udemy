package section4_effectful

import fs2._
import cats.effect._

import scala.concurrent.duration.DurationInt

object Retry extends IOApp.Simple {
  override def run: IO[Unit] = {
    def doEffectFailing[A](io: IO[A]): IO[A] = {
      IO(math.random()).flatMap { flag =>
        if(flag < 0.5) IO.println("Failing........") *> IO.raiseError(new Exception("boom"))
        else IO.println("Successful!") *> io
      }
    }

    Stream.eval(doEffectFailing(IO(42)))
      .compile
      .toList
      .flatMap(IO.println)

    Stream.retry(
      fo = doEffectFailing(IO(42)),
      delay = 1.second,
      nextDelay = _ * 2,
      maxAttempts = 3
    ).compile.drain
  }
}