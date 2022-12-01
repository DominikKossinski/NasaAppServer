package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.kossa.nasa.app.server.architecture.BaseRestController
import pl.kossa.nasa.app.server.db.data.Article
import retrofit2.http.GET

@RestController
@RequestMapping("/api/saved-articles")
class SavedArticlesRestController : BaseRestController() {

    @Autowired
    protected lateinit var savedArticleService: SavedArticleService


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getSavedArticles(): List<Article> {
        val user = getUserDetails()
        val savedArticles = savedArticleService.getByUserId(user.id)
        return savedArticles.map { it.article }
    }
}