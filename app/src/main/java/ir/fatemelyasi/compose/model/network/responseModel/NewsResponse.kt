package ir.fatemelyasi.compose.model.network.responseModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String, // ok
    @SerialName("totalResults")
    val totalResults: Int // 883
) {
    @Serializable
    data class Article(
        @SerialName("author")
        val author: String, // Carl Franzen
        @SerialName("content")
        val content: String, // Join our daily and weekly newsletters for the latest updates and exclusive content on industry-leading AI coverage. Learn MoreSpeech recognition models have grown increasingly accurate in recent ye… [+5482 chars]
        @SerialName("description")
        val description: String, // The model’s architecture integrates keyword spotting directly into the transcription process, allowing Jargonic to maintain accuracy...
        @SerialName("publishedAt")
        val publishedAt: String, // 2025-03-31T13:00:00Z
        @SerialName("source")
        val source: Source,
        @SerialName("title")
        val title: String, // A new, enterprise-specific AI speech model is here: Jargonic from aiOla claims to best rivals at your business’s lingo
        @SerialName("url")
        val url: String, // https://venturebeat.com/ai/a-new-enterprise-specific-ai-speech-model-is-here-jargonic-from-aiola-claims-to-best-rivals-at-your-businesss-lingo/
        @SerialName("urlToImage")
        val urlToImage: String // https://venturebeat.com/wp-content/uploads/2025/03/cfr0z3n_a_robot_with_headphones_emitting_visible_sound_waves_in_4f884cad-1490-4163-977a-c8fb88af3c56.png?w=1024?w=1200&strip=all
    ) {
        @Serializable
        data class Source(
            @SerialName("id")
            val id: String, // business-insider
            @SerialName("name")
            val name: String // VentureBeat
        )
    }
}