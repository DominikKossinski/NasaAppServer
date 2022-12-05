package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.ArticleComment
import pl.kossa.nasa.app.server.db.repositories.ArticleCommentRepository
import java.util.*

@Service("ArticleCommentService")
class ArticleCommentService {

    @Autowired
    private lateinit var articleCommentRepository: ArticleCommentRepository

    @Autowired
    private lateinit var articlesService: ArticlesService

    @Autowired
    private lateinit var userService: UserService

    suspend fun saveComment(articleDate: Date, comment: String, userId: String): ArticleComment {
        val user = userService.getUserById(userId) ?: throw Exception() // TODO exceptions
        val article = articlesService.getArticleByDate(articleDate)
        val articleComment = ArticleComment(0, comment, Date(), null, article, user)
        return articleCommentRepository.save(articleComment)
    }

    suspend fun updateComment(articleDate: Date, commentId: Int, comment: String, userId: String): ArticleComment {
        userService.getUserById(userId) ?: throw Exception() // TODO exception
        articlesService.getArticleByDate(articleDate)
        val articleComment = articleCommentRepository.findByIdOrNull(commentId) ?: throw Exception()
        if (articleComment.author.id != userId) throw Exception() // TODO
        return articleCommentRepository.save(
            articleComment.copy(
                comment = comment,
                updatedAt = Date()
            )
        )
    }

    suspend fun getArticlesComments(articleDate: Date): List<ArticleComment> {
        return articleCommentRepository.findAllByArticleDate(articleDate)
    }
}