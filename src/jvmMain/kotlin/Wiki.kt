import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.locations.*
import io.ktor.sessions.*
import io.ktor.util.*

fun Route.wiki(articles: List<WikiArticle>) {
    fun enoughArticles(articles: List<WikiArticle>) : Boolean { return articles.size >= 5 }

    get<Wiki> {
        call.respond(if (enoughArticles(articles)) articles else listOf())
    }

    post<Wiki> {
        articleList += call.receive<WikiArticle>()
        call.respond(HttpStatusCode.OK)
    }


}



