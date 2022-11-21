package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.extensions.toApiString
import pl.kossa.nasa.app.server.nasa.api.NASAApi
import pl.kossa.nasa.app.server.nasa.models.NASAArticle
import java.util.Date

@Service("NASAArticlesService")
class NASAArticlesService {

    @Autowired
    private lateinit var nasaApi: NASAApi

    suspend fun getArticleByDate(date: Date): NASAArticle? {
        val articleResponse = nasaApi.getArticle(
            date.toApiString(), System.getenv("NASA_API_KEY")
        )
        return articleResponse.body
    }

    suspend fun getArticlesByDateRange(from: Date, to: Date): List<NASAArticle> {
        val articlesResponse = nasaApi.getArticles(
            from.toApiString(), to.toApiString(), System.getenv("NASA_API_KEY")
        )
        return articlesResponse.body ?: emptyList()
    }
}