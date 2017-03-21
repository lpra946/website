package client

import client.Pages.{Home, Login}
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

  case object HomeLocation extends Location

  val routerConfig = RouterConfigDsl[Location].buildConfig { dsl =>
    import dsl._

    (emptyRule
      |staticRoute(root, HomeLocation) ~> render(Login.component)
      ).notFound(redirectToPage(HomeLocation)(Redirect.Replace))
  }


  @JSExport
  def main(): Unit = {

    val router = Router(BaseUrl.fromWindowOrigin , routerConfig)
    router() render dom.document.body
  }


}