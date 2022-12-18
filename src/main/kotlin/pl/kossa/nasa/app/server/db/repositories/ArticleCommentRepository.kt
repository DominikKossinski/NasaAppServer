package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.ArticleComment
import java.util.Date

interface ArticleCommentRepository : CrudRepository<ArticleComment, Int> {

    fun findAllByArticleDate(articleDate: Date): List<ArticleComment>


    @Query(value = "select c from ArticleComment c where c.id = ?1 AND c.author.id = ?2")
    fun findByIdAndUserId(id: Int, userId: String): ArticleComment?
}