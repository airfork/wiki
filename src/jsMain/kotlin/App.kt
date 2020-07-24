import react.*
import react.dom.*
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
           route("/test") {
               div {
                   h1 {
                       +"Test"
                   }
               }
           }
       }
    }