package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.ArticleComment
import java.time.LocalDate

interface ArticleCommentRepository : CrudRepository<ArticleComment, Int> {

    fun findAllByArticleDate(articleDate: LocalDate): List<ArticleComment>


    @Query(value = "select c from ArticleComment c where c.id = ?1 AND c.author.id = ?2")
    fun findByIdAndUserId(id: Int, userId: String): ArticleComment?
}