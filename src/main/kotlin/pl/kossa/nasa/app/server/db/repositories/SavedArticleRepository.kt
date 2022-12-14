package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.SavedArticle
import java.time.LocalDate

interface SavedArticleRepository : CrudRepository<SavedArticle, Int> {

    @Query(value = "select a from SavedArticle a where a.user.id = ?1")
    fun findAllByUserId(userId: String): List<SavedArticle>


    @Query(value = "select a from SavedArticle a where a.user.id = ?1 AND a.article.date = ?2")
    fun findByUserIdAndDate(userId: String, date: LocalDate): SavedArticle?

}