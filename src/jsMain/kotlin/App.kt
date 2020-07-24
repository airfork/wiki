import react.*
import react.router.dom.*

fun RBuilder.app() =
    browserRouter {
       switch {
           route("/", exact = true) {
               div {
                   h1 {
                       +"Kotlin"
                   }
               }
           }
       }
    }