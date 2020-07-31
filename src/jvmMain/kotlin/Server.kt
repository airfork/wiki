import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.locations.*
import org.litote.kmongo.*
import org.litote.kmongo.async.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.async.getCollection
import com.mongodb.ConnectionString

// Sample data
val shoppingList = mutableListOf(
    ShoppingListItem("Cucumbers 🥒", 1),
    ShoppingListItem("Tomatoes 🍅", 2),
    ShoppingListItem("Orange Juice 🍊", 3)
)

val articleList = mutableListOf(
    WikiArticle("My First Wiki Post", listOf("Tunji Afolabi-Brown"),
        "This is my first wiki 'post'. Hopefully it's informative", "Today (No date format)",
        "Today (No date format)"),
    WikiArticle("My Second Wiki Post", listOf("Tunji Afolabi-Brown"),
        "If I told you 2 + 2 equals 5, would you believe me?", "Today (No date format)",
        "Today (No date format)"),
    WikiArticle("My Third Wiki Post", listOf("Tunji Afolabi-Brown"),
        "Let me tell tell you a secret", "Today (No date format)",
        "Today (No date format)"),
    WikiArticle("My Fourth Wiki Post", listOf("Tunji Afolabi-Brown"),
"Don't go to the moon, you'll be corrupted", "Today (No date format)",
"Today (No date format)"),
    WikiArticle("My Fifth Wiki Post", listOf("Tunji Afolabi-Brown"),
"Merlin Hermes, the wandering magician, and his fully automated wishing machine!", "Today (No date format)",
"Today (No date format)")
)

@Location("/")
class Wiki()

fun main() {
    // Create server
    embeddedServer(Netty, 9090) {
        // This allows classes decorated with @Serializable to be parsed as json
        install(ContentNegotiation) {
            json()
        }
        // Allow cross origin requests
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        install(Locations)
        routing {
            route(ShoppingListItem.path) {
                get {
                    call.respond(shoppingList)
                }
                post {
                    shoppingList += call.receive<ShoppingListItem>()
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    shoppingList.removeIf { it.id == id }
                    call.respond(HttpStatusCode.OK)
                }
            }
            route(WikiArticle.path) {
                wiki(articleList)
            }
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }
        }
        install(StatusPages) {
            status(HttpStatusCode.NotFound) {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
        }
    }.start(wait = true)
}