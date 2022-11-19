package pl.kossa.nasa.app.server.restcontrollers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.kossa.nasa.app.server.architecture.BaseRestController
import pl.kossa.nasa.app.server.db.data.Article
import pl.kossa.nasa.app.server.db.data.NasaMediaType
import java.util.*

@RestController
@RequestMapping("/api/articles")
class ArticlesRestController : BaseRestController() {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getArticles(): List<Article> {
        return emptyList()
    }

    @GetMapping("/api/articles/{date}")
    suspend fun getArticleByDate(@PathVariable("date") date: String): Article {
        return Article(Date(), "", "", NasaMediaType.IMAGE, "Title", "", null)
    }
}