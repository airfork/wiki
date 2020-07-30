import io.ktor.application.*

class Wiki {
    companion object {
        private fun enoughArticles(articles: List<WikiArticle>) : Boolean { return articles.size >= 5 }

        fun index(articles: List<WikiArticle>) : List<WikiArticle> {
            return if (enoughArticles(articles)) articles else listOf()
        }


    }
}