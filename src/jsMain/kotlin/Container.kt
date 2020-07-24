@file:JsModule("@material-ui/core/Container")
@file: JsNonModule

import react.*

@JsName("default")
external val container: RClass<ContainerProps>

external interface ContainerProps : RProps {
    var maxWidth: String
}