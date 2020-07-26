import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*
import kotlinx.coroutines.*
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.*
import kotlinx.html.style
import styled.*

val scope = MainScope()

val TestPage = functionalComponent<RProps> { _ ->
    val (shoppingList, setShoppingList) = useState(emptyList<ShoppingListItem>())

    useEffect(dependencies = listOf()) {
        scope.launch {
            setShoppingList(getShoppingList())
        }
    }
    baseline {
        container {
            attrs.maxWidth = "md"
            h1 {
                +"Full-Stack Shopping List!"
            }
            ul {
                shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
                    li {
                        key = item.toString()
                        +"[${item.priority}] ${item.desc} "
                    }
                }
            }
            mbutton {
                attrs.variant = "contained"
                attrs.color = "primary"
                +"Hello There"
            }
        }
    }
}