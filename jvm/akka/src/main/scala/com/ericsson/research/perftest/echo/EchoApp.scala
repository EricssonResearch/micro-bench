package com.ericsson.research.perftest.echo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.pattern.after
import akka.stream.ActorMaterializer
import scala.concurrent.{ Await, ExecutionContext }
import scala.concurrent.duration.{ Duration, DurationInt }
import scala.util.{ Failure, Success }

object EchoApp {

  def main(args: Array[String]): Unit = {
    val interface = sys.env.getOrElse("BIND_INTERFACE", "0.0.0.0")
    val port = sys.env.getOrElse("BIND_PORT", "8080").toInt

    implicit val system = ActorSystem("echo-http-system")
    implicit val executionContext = system.dispatchers.lookup("my-dispatcher")
    implicit val mat = ActorMaterializer()

    Http()
      .bindAndHandle(route(system), interface, port)
      .onComplete {
        case Success(Http.ServerBinding(address)) =>
          system.log.info(s"Successfully bound to $address")
        case Failure(cause) =>
          system.log.error(cause, s"Can't bind to $interface:$port!")
          system.terminate()
      }

    Await.ready(system.whenTerminated, Duration.Inf)
  }

  private def route(system: ActorSystem)(implicit ec: ExecutionContext) = {
    import Directives._

    def terminate = pathSingleSlash {
      delete {
        complete {
          val delay = 500.milliseconds
          system.log.warning("Terminating in $delay")
          after(delay, system.scheduler) { system.terminate() }
          "Terminating!"
        }
      }
    }

    def echo = path("EchoService" / "echo") {
      post {
        entity(as[String]) { body => complete {
          body
        }
        }
      }
    }

    terminate ~ echo
  }
}
