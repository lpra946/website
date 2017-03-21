package client

import client.Pages.{Home, Login, Register}
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport


/**
  * Created by luna on 27/01/16.
  */
@JSExport("Main")
object Main extends js.JSApp {
sealed trait Location

  case object LoginLocation extends Location
  case object RegisterLocation extends Location

  val routerConfig = RouterConfigDsl[Location].buildConfig { dsl =>
    import dsl._

    (emptyRule
      |staticRoute(root, LoginLocation) ~> render(Login.component)
      |staticRoute("#register", RegisterLocation) ~> render(Register.component)
      ).notFound(redirectToPage(LoginLocation)(Redirect.Replace))
  }


  @JSExport
  def main(): Unit = {

    val router = Router(BaseUrl(dom.window.location.href.takeWhile(_ != '#')), routerConfig)
    router() render dom.document.body
  }


}