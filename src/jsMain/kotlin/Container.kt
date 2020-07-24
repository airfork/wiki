@file:JsModule("@material-ui/core/styles")
@file: JsNonModule

import kotlinext.js.JsObject
import react.*

@JsName("makeStyles")
external val styles: RClass<StylesProps>

external interface StylesProps : RProps {
    var style: JsObject
}