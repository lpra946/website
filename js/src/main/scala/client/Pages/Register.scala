package client.Pages
import japgolly.scalajs.react.vdom.prefix_<^.{<, _}

/**
  * Created by Luna on 21/03/2017.
  */
object Register {

  lazy val component = <.div(
    <.div(
      <.label("First Name"),
      <.input(^.tpe := "text", ^.placeholder:="First Name")
    ),
    <.div(
      <.label("Last Name"),
      <.input(^.tpe := "text", ^.placeholder:="Last Name")
    ),
    <.div(
      <.label("Username"),
      <.input(^.tpe := "text", ^.placeholder:="Username")
    ),
    <.div(
      <.label("Password"),
      <.input(^.tpe := "text", ^.placeholder:="Password")
    ),
    <.div(<.button("Register"))
  ).render
}
