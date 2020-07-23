import react.*
import react.dom.*
import kotlinext.js.*
import kotlinx.html.js.*
import kotlinx.coroutines.*


// Not really sure
val scope = MainScope()

// Functional component
val App = functionalComponent<RProps> { _ ->
    val (shoppingList, setShoppingList) = useState(emptyList<ShoppingListItem>())

    useEffect(dependencies = listOf()) {
        scope.launch {
            setShoppingList(getShoppingList())
        }
    }
    // Calling in our container class component
    container {
        // Setting prop that we defined
        attrs.maxWidth = "md"

        // Regular html tag
        h1 {
            // This is how you add text
            +"Full-Stack Shopping List"
        }
        ul {
            // Iterating over array
            shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
                li {
                    key = item.toString()
                    +"[${item.priority}] ${item.desc} "
                    attrs.onClickFunction = {
                        scope.launch {
                            deleteShoppingListItem(item)
                            setShoppingList(getShoppingList())
                        }
                    }
                }
            }
        }
        // How to call in functional component and set the props
        child(
            InputComponent,
            props = jsObject {
                onSubmit = { input ->
                    val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                    scope.launch {
                        addShoppingListItem(cartItem)
                        setShoppingList(getShoppingList())
                    }
                }
            }
        )
        // Another class component
        mbutton {
            +"Hello"
            attrs.color = "primary"
            attrs.variant = "contained"
        }
    }
}