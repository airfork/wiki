import react.*
import react.dom.*
import react.router.dom.*

fun RBuilder.app() =
    browserRouter {
       switch {
           route("/") {
               div {
                   h1 {
                       +"Kotlin"
                   }
               }
           }
       }
    }