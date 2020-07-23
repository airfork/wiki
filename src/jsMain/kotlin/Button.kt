// React button component from material ui
@file:JsModule("@material-ui/core/button")
@file:JsNonModule

import react.*
// Class component
@JsName("default")
external val mbutton: RClass<ButtonProps>

external interface ButtonProps : RProps {
    var color: String
    var variant: String
}
