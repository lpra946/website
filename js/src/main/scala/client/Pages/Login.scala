package client.Pages
import japgolly.scalajs.react.vdom.prefix_<^.{<, _}
/**
  * Created by Luna on 21/03/2017.
  */
object Login {
  lazy val component = <.div(
    <.div(
      <.label("Username"),
      <.input(^.tpe := "text", ^.placeholder:="Username")
    ),
    <.div(
      <.label("Password"),
      <.input(^.tpe := "text", ^.placeholder:="Password")
    ),
    <.div(<.button("Sign In"))
  ).render

}
