import react.child
import react.dom.render
import kotlin.browser.document

class RootComponent

fun main() {
    // Not sure if better way to do this, but this allows you to render different top level components to different routes, basically
    if(document.getElementById("main") != null) {
        render(document.getElementById("main")) {
            app()
        }
    }

//    if(document.getElementById("main-2") != null) {
//        render(document.getElementById("main-2")) {
//            child(Test)
//        }
//    }
}