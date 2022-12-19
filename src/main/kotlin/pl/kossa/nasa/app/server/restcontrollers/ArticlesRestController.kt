package pl.kossa.nasa.app.server.restcontrollers

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.kossa.nasa.app.server.architecture.BaseRestController
import pl.kossa.nasa.app.server.data.requests.ArticleCommentRequest
import pl.kossa.nasa.app.server.db.data.Article
import pl.kossa.nasa.app.server.db.data.ArticleComment
import pl.kossa.nasa.app.server.errors.NotFoundError
import pl.kossa.nasa.app.server.errors.UnauthorizedError
import pl.kossa.nasa.app.server.services.ArticleCommentService
import pl.kossa.nasa.app.server.services.ArticlesService
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/api/articles")
class ArticlesRestController : BaseRestController() {

    @Autowired
    private lateinit var articlesService: ArticlesService

    @Autowired
    private lateinit var commentService: ArticleCommentService

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
        @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") from: LocalDate,
        @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") to: LocalDate
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
    suspend fun getArticleByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): Article {
        return articlesService.getArticleByDate(date)
    }

    @GetMapping("/{date}/comments")
    suspend fun getArticleComments(
        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: Date
    ): List<ArticleComment> {
        return commentService.getArticlesComments(date)
    }

    @PostMapping("/{date}/comments")
    suspend fun postComment(
        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestBody commentRequest: ArticleCommentRequest
    ) {
        val user = getUserDetails()
        commentService.saveComment(date, commentRequest.comment, user.id)
    }

    @PutMapping("/{date}/comments/{commentId}")
    suspend fun putComment(
        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @PathVariable("commentId") commentId: Int,
        @RequestBody commentRequest: ArticleCommentRequest
    ) {
        val user = getUserDetails()
        commentService.updateComment(date, commentId, commentRequest.comment, user.id)
    }
}