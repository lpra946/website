package server


import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import com.typesafe.config.ConfigFactory
import spray.routing.SimpleRoutingApp

import scala.util.Properties

/**
  * Created by luna on 25/01/16.
  */

object Server extends SimpleRoutingApp   {
    def main(args: Array[String]): Unit = {

        implicit val system = ActorSystem()
        implicit val context = system.dispatcher

       // val requestHandlerActor = system.actorOf(RoundRobinPool(20).props(Props[RequestHandlerActor]), "request-handler-actor")

        val port = Properties.envOrElse("WEBSITE_PORT", "8080").toInt

        startServer("0.0.0.0", port = port) {
            get {
                pathSingleSlash {
                getFromResource("index.html")
            } ~
            getFromResourceDirectory("")

            }
        }
    }
}
