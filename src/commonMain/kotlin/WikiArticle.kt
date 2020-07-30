import kotlinx.serialization.Serializable

@Serializable
data class WikiArticle(
    val title: String,
    val contributors: List<String>,
    val contents: String,
    val created: String = "",
    val updated: String = ""
) {
    companion object {
        const val path = "/wiki"
    }
}