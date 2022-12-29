package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.extensions.toApiString
import pl.kossa.nasa.app.server.nasa.api.NASAApi
import pl.kossa.nasa.app.server.nasa.models.NASAArticle
import java.net.SocketTimeoutException
import java.time.LocalDate

@Service("NASAArticlesService")
class NASAArticlesService {

    @Autowired
    private lateinit var nasaApi: NASAApi

    suspend fun getArticleByDate(date: LocalDate): NASAArticle? {
        // TODO better error handling
        return try {
            val articleResponse = nasaApi.getArticle(
                date.toApiString(), System.getenv("NASA_API_KEY")
            )
            articleResponse.body
        } catch (e: Exception) {
            println("GetByDate $e")
            null
        }
    }

    suspend fun getArticlesByDateRange(from: LocalDate, to: LocalDate): List<NASAArticle> {
        // TODO better error handling
        return try {
            val articlesResponse = nasaApi.getArticles(
                from.toApiString(), to.toApiString(), System.getenv("NASA_API_KEY")
            )
            articlesResponse.body ?: emptyList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }
}