package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.ArticleComment
import java.util.Date

interface ArticleCommentRepository : CrudRepository<ArticleComment, Int> {

    fun getAllByArticleDate(articleDate: Date): List<ArticleComment>
}