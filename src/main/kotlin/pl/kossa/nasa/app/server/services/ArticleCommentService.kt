package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.ArticleComment
import pl.kossa.nasa.app.server.db.repositories.ArticleCommentRepository
import pl.kossa.nasa.app.server.exceptions.ArticleCommentNotFoundException
import pl.kossa.nasa.app.server.exceptions.UserNotFoundException
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
        val user = userService.getUserById(userId) ?: throw UserNotFoundException(userId)
        val article = articlesService.getArticleByDate(articleDate)
        val articleComment = ArticleComment(0, comment, Date(), null, article, user)
        return articleCommentRepository.save(articleComment)
    }

    suspend fun updateComment(articleDate: Date, commentId: Int, comment: String, userId: String): ArticleComment {
        userService.getUserById(userId) ?: throw UserNotFoundException(userId)
        articlesService.getArticleByDate(articleDate)
        val articleComment =
            articleCommentRepository.findByIdAndUserId(commentId, userId) ?: throw ArticleCommentNotFoundException(commentId)
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