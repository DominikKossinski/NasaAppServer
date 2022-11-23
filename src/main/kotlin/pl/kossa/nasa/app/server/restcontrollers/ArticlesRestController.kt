package pl.kossa.nasa.app.server.restcontrollers

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.Query
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.kossa.nasa.app.server.architecture.BaseRestController
import pl.kossa.nasa.app.server.db.data.Article
import pl.kossa.nasa.app.server.db.data.NasaMediaType
import pl.kossa.nasa.app.server.errors.NotFoundError
import pl.kossa.nasa.app.server.errors.UnauthorizedError
import pl.kossa.nasa.app.server.extensions.toApiString
import pl.kossa.nasa.app.server.services.ArticlesService
import java.util.*

@RestController
@RequestMapping("/api/articles")
class ArticlesRestController : BaseRestController() {

    @Autowired
    private lateinit var articlesService: ArticlesService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = [Content(schema = Schema(implementation = UnauthorizedError::class))]
            )
        ]
    )
    suspend fun getArticles(
        @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") from: Date,
        @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") to: Date
    ): List<Article> {
        return articlesService.getArticlesByPage(from, to)
    }

    @GetMapping("/{date}")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200"),
            ApiResponse(
                responseCode = "401",
                description = "Unauthorized",
                content = [Content(schema = Schema(implementation = UnauthorizedError::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Unauthorized",
                content = [Content(schema = Schema(implementation = NotFoundError::class))]
            )
        ]
    )
    suspend fun getArticleByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: Date): Article {
        return articlesService.getArticleByDate(date)
    }
}