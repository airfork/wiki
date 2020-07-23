import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListItem(val desc: String, val priority: Int) {
    val id: Int = desc.hashCode()

    // Works like a static method. No need to have an instance of object to call this
    companion object {
        const val path = "/shoppingList"
    }
}