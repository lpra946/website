package client.Pages

import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by Luna on 15/04/2016.
 */
object Home {

  val styles: ReactAttr = "styles".reactAttr

  lazy val component =
    <.div(^.className:= "header-fixed",
    <.div(^.className:= "wrapper",
    <.div(^.className :="bg-color-darker",
    <.div (^.id :="promo-video", ^.className:="img",
      <.div (^.className:="container valign__middle text-center", ^.paddingTop:= "183.75px",
        <.p (^.className:="color-light margin-bottom-10  fadeInUp wow animated", "data-wow-duration".reactAttr:="1.5s",
          "data-wow-delay".reactAttr :=".5s",
        <.span(^.className:= "home-sub-heading", "NEW - SLEEK - STYLISH - MODERN")
        ),
    <.h2(^.className:="color-light  fadeInUp wow margin-bottom-20 animated",
    "data-wow-duration".reactAttr:="1.5s", "data-wow-delay".reactAttr:="1s",
    <.span(^.className:= "home-heading","\"Imagination is the only weapon in the war against reality\"")),
        <.p(^.className:="color-light font-open-sans margin-bottom-20  fadeInUp wow animated",
          "data-wow-duration".reactAttr:="1.5s", "data-wow-delay".reactAttr:="1.5s",
          <.span(^.className:= "author", "-Jules de Gaultier"))

      )

    )
    )
    )
  ).render

  val homeComponent = <.div(^.id:="body",
    "data-spy".reactAttr:="scroll", "data-target".reactAttr:=".one-page-header",
    ^.className:="demo-lightbox-gallery dark", <.section(
      ^.id:="intro", ^.className:="intro-section",
      <.div (^.className := "fullscreenbanner-container",
        <.div (^.className:="fullscreenbanner",
          <.ul(
//Slide 1
            <.li("data-transition".reactAttr:="curtain-1",
            "data-slotamount".reactAttr:="5", "data-masterspeed".reactAttr:="700",
            "data-title".reactAttr:="Slide 1",
              //Main Image
              <.img(^.src:="../assets/img/image.jpg",
                ^.alt:="slidebg1", "data-bgfit".reactAttr:="cover",
                "data-bgposition".reactAttr:="center center", "data-bgrepeat".reactAttr:="no-repeat"),

                // LAYERS
                <.div(^.className:="tp-caption rs-caption-1 sft start",
                  "data-x".reactAttr:="center", "data-hoffset".reactAttr:="0",
                  "data-y".reactAttr:="100", "data-speed".reactAttr:="800",
                  "data-start".reactAttr:="2000", "data-easing".reactAttr:="Back.easeInOut",
                  "data-endspeed".reactAttr:="300",
                  "WE ARE UNIFY CREATIVE TECHNOLOGY COMPANY"),
                //LAYER
                <.div (^.className:="tp-caption rs-caption-2 sft",
                  "data-x".reactAttr:="center", "data-hoffset".reactAttr:="0",
                  "data-y".reactAttr:="200", "data-speed".reactAttr:="1000",
                  "data-start".reactAttr:="3000", "data-easing".reactAttr:="Power4.easeOut",
                  "data-endspeed".reactAttr:="300", "data-endeasing".reactAttr:="Power1.easeIn",
                  "data-captionhidden".reactAttr:="off",
                    <.span(^.className:= "layers", "Creative freedom matters user experience.",
                      <.br,"We minimize the gap between technology and its audience.")),
              //LAYER
              <.div(^.className:="tp-caption rs-caption-3 sft",
                "data-x".reactAttr:="center", "data-hoffset".reactAttr:="0",
                "data-y".reactAttr:="360", "data-speed".reactAttr:="800",
                "data-start".reactAttr:="3500", "data-easing".reactAttr:="Power4.easeOut",
                "data-endspeed".reactAttr:="300", "data-endeasing".reactAttr:="Power1.easeIn",
                "data-captionhidden".reactAttr:="off",<.span(^.className:= "layers"))
            )
          )
        )
      )
    )
  ).render
}
